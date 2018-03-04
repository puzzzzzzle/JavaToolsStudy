package group.zhangtao.classStudy;

import group.zhangtao.io.zip.ZipFunctions;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassStudy {
    public static void main(String[] args) {
        Class c = SubSample.class;
        Method [ ] methods = c.getMethods();
        Arrays.asList(methods).forEach((n)-> System.out.println(n.getName()));
        Arrays.asList(c.getAnnotations()).forEach((n)-> System.out.println(n.annotationType()));
    }

}
