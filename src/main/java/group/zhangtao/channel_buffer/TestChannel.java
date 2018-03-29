package group.zhangtao.channel_buffer;

//通道用于源节点与目标节点之间的连接，在NIO中负责缓冲区中数据的传输。
//Channel本身不存储数据，需要配合缓冲区进行数据传输

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * 通道的主要实现类
 * java.nio.channels.Channel接口
 *      |--FileChannel
 *      |--SocketChannel
 *      |--ServerSocketChannel
 *      |--DatagramChannel
 *
 * 获取通道
 * 1.Java针对支持通道的类提供了getChannel()方法
 *      本地IO：
 *          FileInputStream/FileOutputStream
 *          RandomAccessFile
 *
 *      网络IO：
 *          Socket
 *          ServerSocket
 *          DatagramSocket
 *
 * 2.在JDK1.7中NIO.2针对各个通道提供了静态方法open()
 * 3.在JDK1.7中NIO.2的Files工具类的newByteChannel()
 */


public class TestChannel {
    private static String  from = "TestFile/Guider.tar.gz";
    private static String  to = "TestFile/GuiderCp.tar.gz";
    private static String randomFrom ="TestFile/index.json";
    private static String randomto ="TestFile/indexCopy.json";

    //利用通道完成文件的复制(非直接缓冲区)
    @Test
    public void test1() throws IOException {
        //26088
        //6479
        //6587
        //6464
        long start = System.currentTimeMillis();
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fileInputStream = new FileInputStream(from);
            fileOutputStream = new FileOutputStream(to);
            //获取通道
            inChannel = fileInputStream.getChannel();
            outChannel = fileOutputStream.getChannel();
            //分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //将通道中的数据存入缓冲区
            while (inChannel.read(buffer) != -1) {
                //切换成读取数据模式
                buffer.flip();
                //将缓冲区中的数据写入通道中
                outChannel.write(buffer);
                //清空缓冲区
                buffer.clear();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭通道 省略if判断
            outChannel.close();
            inChannel.close();
            //关闭流
            fileOutputStream.close();
            fileInputStream.close();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void test2() throws IOException {
        //1190
        //5144
        //5932
        //1316
        //1126
        //998
        //1242
        long start = System.currentTimeMillis();
        //使用直接缓冲区完成文件的复制（内存映射文件）
        //只有ByteBuffer支持直接缓冲区
        FileChannel inChannel = FileChannel.open(Paths.get(from), StandardOpenOption.READ);
        //StandardOpenOption.CREATE_NEW若存在则报错
        //StandardOpenOption.CREATE若存在则覆盖
        FileChannel outChannel = FileChannel.open(Paths.get(to), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        //内存映射文件。缓冲区在物理内存中。
        MappedByteBuffer inMappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据读写操作
        byte[] dst = new byte[inMappedByteBuffer.limit()];
        inMappedByteBuffer.get(dst);
        outMappedByteBuffer.put(dst);

        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void test3() throws IOException {
        //将数据从源通道传输到其他 Channel 中
        //729
        //684
        //601
        //706
        //625
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get(from), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(to), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        //通道之间的数据传输
        //inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void test4() throws IOException {
        RandomAccessFile randomAccessFile1 = new RandomAccessFile(randomFrom, "rw");
        //获取通道
        FileChannel fileChannel1 = randomAccessFile1.getChannel();
        //分配指定大小的缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        //分散读取,从 Channel 中读取的数据“分散” 到多个 Buffer 中
        ByteBuffer[] buffers = {buffer1, buffer2};
        fileChannel1.read(buffers);
        for (int i = 0; i < buffers.length; i++) {
            buffers[i].flip();
        }
        System.out.println(new String(buffers[0].array(), 0, buffers[0].limit()));
        System.out.println("-------------------");
        System.out.println(new String(buffers[1].array(), 0, buffers[1].limit()));
        //聚集写入,将多个 Buffer 中的数据“聚集”到 Channel
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(randomto, "rw");
        FileChannel fileChannel2 = randomAccessFile2.getChannel();
        fileChannel2.write(buffers);
    }

    @Test
    public void test5() {
        /**
         * 编码：字符串->字节数组
         * 解码：字节数组->字符串
         */
        //获取所有支持的字符集
        SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set = availableCharsets.entrySet();
        for (Map.Entry<String, Charset> entry: set) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }
    }

    @Test
    public void test6() throws CharacterCodingException {
        Charset charset = Charset.forName("GBK");
        //编码器
        CharsetEncoder charsetEncoder = charset.newEncoder();
        //解码器
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("你好");
        charBuffer.flip();
        //编码
        ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);

        for (int i = 0; i < 4; i++) {
            //字节数组
            System.out.println(byteBuffer.get());
        }
        //切换到读模式
        byteBuffer.flip();
        //解码
        CharBuffer charBuffer2 = charsetDecoder.decode(byteBuffer);
        System.out.println(charBuffer2.toString());
    }
}
