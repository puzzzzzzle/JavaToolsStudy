package group.zhangtao.junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JUnitStudy {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MessageTest.class);
        result.getFailures().forEach((i)->{
            System.out.println(i.toString());
        });
    }
}
