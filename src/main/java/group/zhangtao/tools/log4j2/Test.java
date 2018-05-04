package group.zhangtao.tools.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Test {
    public static Logger logger = LogManager.getLogger("tao.study");

    public static void main(String[] args) {
        logger.error("hello log4j2");
        logger.debug("debug");
        logger.debug("okHttpTest tag");
        try {
            byte b[] = new byte[100];
            Arrays.asList(b).forEach(n -> System.out.println(n));
            new FileInputStream("none").read(b);
        } catch (FileNotFoundException e) {
            logger.trace("file not exists", e);
        } catch (IOException e) {
            logger.trace("io exception", e);
        }
    }
}
