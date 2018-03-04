package group.zhangtao.io.stream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class StreamMap {
    public static void main(String[] args) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(
                System.in
        )) {
            Scanner scanner = new Scanner(bufferedInputStream);
            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                if (temp.toUpperCase().equals("BYE")) {
                    break;
                }
                System.out.println(temp);
            }
            InputStream inputStream = bufferedInputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
