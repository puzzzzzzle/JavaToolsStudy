package group.zhangtao.netio.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketStudy extends Thread {

    private static SocketStudy socketStudy;

    private SocketStudy() {
    }

    public static SocketStudy getSocketStudy() {
        if (socketStudy == null) {
            return new SocketStudy();
        } else {
            return socketStudy;
        }
    }

    public static void main(String[] args) {
        SocketStudy socketStudy = SocketStudy.getSocketStudy();
        socketStudy.run();
    }

    @Override
    public void run() {
        try (
                ServerSocket serverSocket = new ServerSocket(8189)
        ) {
            int i = 0;
            while (true) {
                Socket in = serverSocket.accept();
                Thread server = new MySocketServer(in);
                server.start();
                System.out.println("now:" + ++i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MySocketServer extends Thread {
        Socket incoming;

        public MySocketServer(Socket socket) {
            incoming = socket;
        }

        private void singleSecket() {
            try {
                InputStream inputStream = incoming.getInputStream();
                OutputStream outputStream = incoming.getOutputStream();
                try (
                        Scanner scanner = new Scanner(inputStream);
                        PrintWriter printWriter = new PrintWriter(outputStream, true)
                ) {
                    String line = "";
                    for (int i = 0; i < 7 && scanner.hasNextLine(); i++) {
                        String temp = scanner.nextLine();
                        line += temp;
                        System.out.println(temp);
                    }
                    String salt = line.substring(line.indexOf("salt") + 5, line.indexOf("salt") + 18);
                    System.out.println(salt);
//                        System.out.println(salt.length());
                    String result = "HTTP/1.1 200 OK\n" +
                            "Server: fasthttp\n" +
                            "Date: Mon, 25 Sep 2017 11:05:33 GMT\n" +
                            "Content-Type: text/plain; charset=utf-8\n" +
                            "Content-Length: 398\n" +
                            "\n" +
                            "<!-- 1f690d50a5fa8ed9d031a0cf7e4e323e0a2436dbd1417d755e2e075585a0d9e75cc90aad7a2830a5e63abbaaecf35b79e9cc951fddfa299daa8eaa953b27c7f3 -->\n" +
                            "                                                                                                                                         <ObtainTicketResponse><message></message><prolongationPeriod>607875500</prolongationPeriod><responseCode>OK</responseCode><salt>" + salt + "</salt><ticketId>1</ticketId><ticketProperties>licensee=ilanyu\tlicenseType=0\t</ticketProperties></ObtainTicketResponse>\n";
                    System.out.println(result);
                    printWriter.println(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    incoming.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            singleSecket();
        }
    }
}

