package group.zhangtao.io.stream.java8.stream;

import java.util.stream.IntStream;

public class streamTest {
    public static void main(String[] args) {

        IntStream.range(0, 10).forEach((n) -> System.out.println(n));

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
