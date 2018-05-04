package group.zhangtao.grammer;

import org.apache.tools.ant.taskdefs.Sleep;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class MStream {
    /*

    Stream的操作有Intermediate、Terminal和Short-circuiting：
    Intermediate：map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 skip、 parallel、 sequential、 unordered
    Terminal：forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、iterator
    Short-circuiting
    anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit

    我们有多种方式生成Stream:
    Stream接口的静态工厂方法（注意：Java8里接口可以带静态方法）；
    Collection接口和数组的默认方法（默认方法,也使Java的新特性之一，后续介绍），把一个Collection对象转换成Stream
    其他
        Random.ints()
        BitSet.stream()
        Pattern.splitAsStream(java.lang.CharSequence)
        JarFile.stream()
     */
    public static void main(String[] args) {
//        IntStream.range(0,100).forEach(System.out::println);

        System.out.println(Integer.MAX_VALUE);
        //            if(i==null)
//
        Stream.generate(new Supplier<Integer>() {
            int i =1;
            int j= 1;
            @Override
            public Integer get() {
                int temp = i+j;
                i = j;
                j = temp;
                return j;
            }
        }).forEach(System.out::println);
    }
}
