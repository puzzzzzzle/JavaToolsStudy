package group.zhangtao.netio.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

public class HttpTest {
    public static void main(String[] args) {
        new HttpTest().urlHttp();
        System.out.println("++++++++++++++++++");
        new HttpTest().socketHttp();

    }

    private void socketHttp() {
        try {
            try (
                    Socket socket = new Socket("www.baidu.com", 80); Scanner scanner = new Scanner(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    socket.getInputStream()
                            )
                    )
            );
                    PrintWriter out = new PrintWriter(
                            new BufferedOutputStream(
                                    socket.getOutputStream()
                            )
                    )) {
                socket.setSoTimeout(15000);
                out.println("GET / HTTP/1.1");
                out.println("Host: www.baidu.com:80");
                out.println("\r\n");
                out.flush();

                String line;
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    System.out.println(line);
                    if (line.contains("+ new Date(new Date().getTime() + 10*60*1000).toGMTString();</script>")) {
                        break;
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void urlHttp() {
        URL url = null;
        try {
            url = new URL("http://www.baidu.com");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            try (Scanner scanner = new Scanner(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    httpURLConnection.getInputStream()
                            ), "UTF-8"
                    )
            )) {
                String line;
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    System.out.println(line);
                    if (line.contains("+ new Date(new Date().getTime() + 10*60*1000).toGMTString();</script>")) {
                        break;
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
