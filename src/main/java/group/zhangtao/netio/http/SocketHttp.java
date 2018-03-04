package group.zhangtao.netio.http;

import java.io.*;
import java.net.Socket;

public class SocketHttp {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("www.baidu.com", 80);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                socket.getOutputStream()));
        bw.write("GET / HTTP/1.1\r\n");
        bw.write("Host: www.baidu.com\r\n");
        bw.write("\r\n");
        bw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            if (line.contains("</html>")) {
                break;
            }
        }
        br.close();
        bw.close();
        socket.close();
    }
}
