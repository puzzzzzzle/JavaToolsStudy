package group.zhangtao.netio.http;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TimeError {
    /**
     * 测试时间问题
     *
     * @param host
     */
    private static void test(String host) {
        try (

                Socket socket = new Socket("www.baidu.com", 80);
                PrintWriter out = new PrintWriter(
                        new BufferedOutputStream(
                                socket.getOutputStream()
                        )
                );
//                Scanner scanner = new Scanner(
//                        new InputStreamReader(
//                                new BufferedInputStream(
//                                        socket.getInputStream()
//                                )
//                        )
//                )
        ) {
            //http header
            socket.setSoTimeout(15000);
            out.println("GET / HTTP/1.1");
            out.println("Host: www.baidu.com:80");
            out.println("\r\n");
            out.flush();

            //test body
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length=inputStream.read(bytes,0,bytes.length))>0){
                byteArrayOutputStream.write(bytes,0,length);
                System.out.println(length);
            }
            System.out.println(new String(byteArrayOutputStream.toByteArray(),"UTF-8"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test(" www.baidu.com:80");
    }
}
