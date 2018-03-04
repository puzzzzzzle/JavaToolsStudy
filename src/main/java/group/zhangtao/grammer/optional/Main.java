package group.zhangtao.grammer.optional;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.empty();
        System.out.println(name.orElseGet(()->"none"));
//        name = Optional.ofNullable(null);
//        name = Optional.ofNullable("hello");
        System.out.println(name.isPresent());
        name.ifPresent(n-> System.out.println(n));
//        name.ifPresentOrElse(n-> System.out.println(n),()-> System.out.println("none"));

    }
}
