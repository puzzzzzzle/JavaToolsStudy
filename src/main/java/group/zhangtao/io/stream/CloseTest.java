package group.zhangtao.io.stream;

import java.io.*;

public class CloseTest {
    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("test", true);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
            outputStreamWriter.write("hello!!");
            outputStreamWriter.flush();
            bufferedOutputStream.close();
            BufferedOutputStream bufferedOutputStream1 = new BufferedOutputStream(fileOutputStream);
            OutputStreamWriter outputStreamWriter1 = new OutputStreamWriter(bufferedOutputStream1, "UTF-8");
            outputStreamWriter1.write("hello1");
            outputStreamWriter1.flush();
            outputStreamWriter1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
