package group.zhangtao.netio.Socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 1017)) {
            String line = "GET /rpc/obtainTicket.action?buildDate=20170329&buildNumber=2017.1.3+Build+CL-171.4694.31&clientVersion=4&hostName=Tao&machineId=d4028a0d-a481-4d1b-8d79-7072721b8e99&productCode=cfc7082d-ae43-4978-a2a2-46feb1679405&productFamilyId=cfc7082d-ae43-4978-a2a2-46feb1679405&salt=1506272189678&secure=false&userName=Me&version=2017100&versionNumber=2017100 HTTP/1.1\n" +
                    "Accept-Encoding: gzip\n" +
                    "User-Agent: Java/1.8.0_112-release\n" +
                    "Host: 127.0.0.1:8189\n" +
                    "Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\n" +
                    "Connection: keep-alive";
            socket.getOutputStream().write(line.getBytes());
            Scanner scanner = new Scanner(socket.getInputStream());
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
