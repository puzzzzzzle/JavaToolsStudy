package group.zhangtao.grammer.optionaltest;

import java.util.Arrays;
import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("default");
        Arrays.asList(int.class.getMethods()).forEach(n-> System.out.println(n.getName()));
        Class intClass = int.class;
        Class integerClass = Integer.class;
        System.out.println(intClass.getName());
        System.out.println(integerClass.getName());

    }
}
