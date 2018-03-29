package group.zhangtao.netio.http;

import java.io.*;
import java.net.Socket;

public class SocketHttp {
    public static void main(String[] args) throws IOException {
        String host = "Host:www.baidu.com\r\n";
        Socket socket = new Socket("www.baidu.com", 80);
//        socket.setSoTimeout(5000);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                socket.getOutputStream()));
        bw.write("GET / HTTP/1.1\r\n");
        bw.write(host);
        bw.write("\r\n");
        bw.flush();
//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                socket.getInputStream()));
//        String line = null;


        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        byte [] bytes = new byte[1024*1024];
        int length=0;
        while ((length = bufferedInputStream.read(bytes,0,bytes.length))>0) {
            byteout.write(bytes,0,length);
            System.out.println(length);
        }
        System.out.println(byteout.toString());

        bw.close();
        socket.close();
    }
}
