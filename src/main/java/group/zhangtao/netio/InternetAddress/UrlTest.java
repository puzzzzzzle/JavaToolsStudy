package group.zhangtao.netio.InternetAddress;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class UrlTest {
    public static void main(String[] args) {
        String workRootPath = new File("").getAbsolutePath();

        try {
//            URL mytext  = new URL("file:///"+workRootPath+File.separator+"test");
            URL mytext = new URL("http://www.baidu.com");

            URLConnection urlConnection = (URLConnection) mytext.openConnection();
            urlConnection.setDoOutput(true);
            try (InputStream inputStream = urlConnection.getInputStream();
            ) {
                Scanner scanner = new Scanner(
                        new BufferedInputStream(
                                inputStream
                        )
                );
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
