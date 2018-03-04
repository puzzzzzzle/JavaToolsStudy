package group.zhangtao.grammer.functional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyStream {
    private static void test(Stream<String> stream){
        long t0 = System.nanoTime();
        stream.sorted().count();
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("time: %d ms", millis));
    }

    public static void main(String[] args) {
        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());

        myFunction();
        copiedFunction();
    }
    private static void myFunction(){
        List<String> values = new LinkedList<>();
        List<String> values1 = new LinkedList<>();
        IntStream.range(0,1000000).forEach(n-> {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
            values1.add(uuid.toString());
        });
        System.out.print("stream:   ");
        test(values.stream());
        System.out.print("parallelStream:   ");
        test(values1.parallelStream());
    }
    private static void copiedFunction(){

        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));

    }
}
