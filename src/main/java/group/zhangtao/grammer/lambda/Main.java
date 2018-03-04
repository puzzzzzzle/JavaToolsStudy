package group.zhangtao.grammer.lambda;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
            }
        })
//                .start()
        ;
        System.out.println("main thread end");
        // Java 8之后：
        List features = Arrays.asList("Lambdas", "Default Method", "MyStream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));
        features.forEach(System.out::println);
        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }
}

