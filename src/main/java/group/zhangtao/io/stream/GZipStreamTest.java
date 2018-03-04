package group.zhangtao.io.stream;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class GZipStreamTest {
    public static void main(String[] args) {
        GZipStreamTest gZipStreamTest = new GZipStreamTest();
        gZipStreamTest.GZipWrite();
    }

    private void GZipWrite() {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                new GZIPOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream("Gzip.gz")
                        )
                )
        )) {
            outputStreamWriter.write("hello world");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
