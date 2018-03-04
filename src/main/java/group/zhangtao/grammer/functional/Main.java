package group.zhangtao.grammer.functional;

import group.zhangtao.tools.settings.StaticFiled;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    private final static Logger logger = StaticFiled.logger;
    public static void main(String[] args) {
        Predicate<String> predicate = o -> o.length()>0;
        Predicate<String> predicate1 =predicate.and(s -> s.startsWith("h"));
        Predicate<String> predicate2 = predicate1.or(s -> s.startsWith("o"));
//        Predicate<String> predicate = o -> o.length()>0;
        Function<String,Integer> praseInt = s -> Integer.parseInt(s);
        logger.debug(predicate.test("hhh"));
        logger.debug(predicate1.test("hhh"));
        logger.debug(predicate1.test("ohh"));
        logger.debug(predicate2.test("ohh"));
        logger.debug(praseInt.apply("1"));
        Stream <Integer> integerStream = Arrays.asList(0,1,2,9,5,11,20).stream();
        integerStream
                .filter(i->i<10)
                .sorted((o1, o2) -> {
                    if (o1 < o2) return 1;
                    return -1;
                })
                .forEach(System.out::println);
    }
}
