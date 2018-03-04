package group.zhangtao.io.stream;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream("test.out")
        )) {
            PrintStream myOut = new PrintStream(bufferedOutputStream);
            myOut.println("hello my out!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
