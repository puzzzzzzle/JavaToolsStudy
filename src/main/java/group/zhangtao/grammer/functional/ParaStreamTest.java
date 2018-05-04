package group.zhangtao.grammer.functional;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParaStreamTest {
    public static void main(String[] args) {
        List<Double> doubles = IntStream
                .range(0, 100000)
                .boxed()
                .map(i -> Math.random() * 1000)
                .collect(Collectors.toList());
        long course = 0;
        int allTimes = 55;
        int prepareTimes = 5;
        System.out.println("prepare:    ");
        for (int i = 0; i < allTimes; i++) {
            long time = testStream(doubles);
            if (i == prepareTimes) {
                System.out.println();
            }
            if (i < prepareTimes) {
                System.out.print(time + "    ");
            } else {
                System.out.print(time + "    ");
                course += time;
            }
        }
        System.out.println("\n" + course / allTimes);


        System.out.println("\nprepare:    ");
        for (int i = 0; i < allTimes; i++) {
            long time = testFor(doubles);
            if (i == prepareTimes) {
                System.out.println();
            }
            if (i < prepareTimes) {
                System.out.print(time + "    ");
            } else {
                System.out.print(time + "    ");
                course += time;
            }
        }
        System.out.println("\n" + course / allTimes);

    }

    private static long testStream(List<Double> doubles) {
        List<Double> temp = new LinkedList<>();
        temp.addAll(doubles);
        long start = System.nanoTime();
        Stream<Double> doubleStream = temp.stream().sorted();
        long end = System.nanoTime();

        //make sure
        doubleStream.reduce((p, c) -> {
            Assert.assertTrue(p <= c);
            return c;
        });
        return end - start;
    }

    private static long testFor(List<Double> doubles) {
        List<Double> temp = new LinkedList<>();
        temp.addAll(doubles);
        long start = System.nanoTime();
        temp.sort(Double::compareTo);
        long end = System.nanoTime();

        temp.stream().reduce((p, c) -> {
            Assert.assertTrue(p <= c);
            return c;
        });
        return end - start;
    }
}
