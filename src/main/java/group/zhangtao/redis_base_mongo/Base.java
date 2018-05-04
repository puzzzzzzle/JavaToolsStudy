package group.zhangtao.redis_base_mongo;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class Base {
    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    private static final Base64.Encoder urlEncoder = Base64.getUrlEncoder();
    private static final Base64.Decoder urlDecoder = Base64.getUrlDecoder();

    public static void main(String[] args) throws IOException {
        try (
                ByteOutputStream byteOutputStream = new ByteOutputStream();
                PrintWriter printWriter = new PrintWriter(
                        new FileOutputStream("picDecode")
                );
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                        new FileOutputStream("picDecode.png")
                )
        ) {
            {
                long start = System.nanoTime();
                BufferedImage bufferedImage = ImageIO.read(new File("pic.png"));
                System.out.println(System.nanoTime() - start);
                ImageIO.write(bufferedImage, "png", byteOutputStream);
                System.out.println(System.nanoTime() - start);
            }
            String name = "sdjakdhkjahdffahgjklhgalkgh司法哈佛框架速度会发卡";
            String strStr = encoder.encodeToString(name.getBytes());
            long start = System.nanoTime();
            String strPic = encoder.encodeToString(byteOutputStream.getBytes());
            System.out.println(System.nanoTime() - start);
            printWriter.write(strPic);
            byte [] bytes = decoder.decode(strStr);
            bufferedOutputStream.write(bytes,0,bytes.length);
//            System.out.println(strPic);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        test();
    }

    private static void testTime() throws IOException {
        ByteOutputStream byteOutputStream = new ByteOutputStream();

        System.out.println("ImageIo Read");
        long start = System.nanoTime();
        BufferedImage bufferedImage = ImageIO.read(new File("pic.png"));
        System.out.println(System.nanoTime() - start);

        System.out.println("ImageIo Write");
        start = System.nanoTime();
        ImageIO.write(bufferedImage, "png", byteOutputStream);
        System.out.println(System.nanoTime() - start);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(
                        new File("pic.png")
                )
        );
        System.out.println("buffered file");
        start = System.nanoTime();
        byte[] bytes = new byte[bufferedInputStream.available()];
//        int len = 0;
        int len = bufferedInputStream.read(bytes, 0, bufferedInputStream.available());
        System.out.println(System.nanoTime() - start);

        Assert.assertEquals(len, bufferedInputStream.available());
    }

    @Test
    public static void test() throws IOException {
        for (int i = 0; i < 20; i++) {
            testTime();
        }
    }
}
