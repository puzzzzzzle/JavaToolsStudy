package group.zhangtao.grammer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MapedFileTest {
    private static final char ENGLISH = 'c';
    private static final char CHINESE = '张';
    private static final byte ZERO = 0x0;

    public static void main(String[] args) throws IOException {
        File file = new File("maped.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
//        writeASCII(mappedByteBuffer);
//        read(mappedByteBuffer,"ASCII");
        writeUTF8(mappedByteBuffer);
    }

    private static void read(MappedByteBuffer mappedByteBuffer, String encoding) throws UnsupportedEncodingException {
        if (encoding.equals("ASCII")) {
            readASCII(mappedByteBuffer);
        }
    }

    /**
     * could not write chinese
     *
     * @param mappedByteBuffer
     * @throws IOException
     */
    private static void writeASCII(MappedByteBuffer mappedByteBuffer) throws IOException {
        mappedByteBuffer.rewind();
        for (int i = 0; i < mappedByteBuffer.limit(); i++) {
            mappedByteBuffer.put(i, (byte) ENGLISH);
        }
    }

    private static void readASCII(MappedByteBuffer mappedByteBuffer) throws UnsupportedEncodingException {
        mappedByteBuffer.rewind();
        System.out.println("通过java String 转码");
        byte[] bytes = new byte[mappedByteBuffer.limit()];
        mappedByteBuffer.get(bytes, 0, mappedByteBuffer.limit());
        String words = new String(bytes, "ASCII");
        System.out.println(words);

        mappedByteBuffer.rewind();
        System.out.println("ascii 的理解转码");
        System.out.println(ENGLISH + "的ascii值：" + (int) ENGLISH);
        System.out.println("0位存储的ascii值：" + mappedByteBuffer.get(0));
        StringBuffer stringBuffer = new StringBuffer();
        mappedByteBuffer.rewind();
        for (int i = 0; i < mappedByteBuffer.limit(); i++) {
            byte b = mappedByteBuffer.get(i);
            stringBuffer.append((char) b);
        }
        System.out.println(stringBuffer.toString());
    }

    private static void writeUTF8(MappedByteBuffer mappedByteBuffer) {
        mappedByteBuffer.rewind();
        int i = 0;
        while (i < mappedByteBuffer.limit()) {
            mappedByteBuffer.put(i, (byte) ENGLISH);
            i += 1;
            byte[] bytes = String.valueOf(CHINESE).getBytes();
            for (byte aByte : bytes) {
                mappedByteBuffer.put(i, aByte);
                i++;
            }
//            mappedByteBuffer.putChar()
        }
    }

    @Test
    public void textChar() {
        testChar((int) CHINESE);
        testChar((int) ENGLISH);
    }

    public void testChar(int charInt) {
        System.out.println((char) charInt);
        System.out.println(charInt);
        System.out.println(charInt & 0xff);
        System.out.println(charInt >> 8 & 0xff);
    }

}
