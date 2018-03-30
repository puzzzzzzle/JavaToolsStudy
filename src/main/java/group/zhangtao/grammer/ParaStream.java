package group.zhangtao.grammer;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ParaStream {
    @Test
    public void test() {
        int max = 10000000;
        long start = System.nanoTime();
        String a = IntStream.range(0, max).collect(StringBuilder::new, (sb, i) -> sb.append(i), StringBuilder::append).toString();
        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();
        String b = IntStream.range(0, max).parallel().collect(StringBuilder::new, (sb, i) -> sb.append(i), StringBuilder::append).toString();
        System.out.println(System.nanoTime() - start);
        Assert.assertEquals(a, b);
    }

    @Test
    public void test2() {
        int max = 10000000;
        Random random = new Random();
        List<Integer> integers = random.ints().limit(max).collect(LinkedList<Integer>::new, (list, i) -> list.add(i), LinkedList::addAll);
        System.out.println("start");
        long start = System.nanoTime();
        String a = integers.stream().sorted().collect(StringBuilder::new, (sb, i) -> sb.append(i), StringBuilder::append).toString();
        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();
        String b = integers.parallelStream().sorted().collect(StringBuilder::new, (sb, i) -> sb.append(i), StringBuilder::append).toString();
        System.out.println(System.nanoTime() - start);
        Assert.assertEquals(a, b);
    }
    /*
    parallelStream 优势在于处理耗时的任务
     */
    @Test
    public void test3() {
        int max = 100;
        Random random = new Random();
        List<Integer> integers = random.ints().limit(max).collect(LinkedList<Integer>::new, (list, i) -> list.add(i), LinkedList::addAll);
        System.out.println("start");
        long start = System.nanoTime();
        String a = integers.stream().sorted().collect(StringBuilder::new,
                (sb, i) -> {
                    sb.append(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                StringBuilder::append).toString();
        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();
        String b = integers.parallelStream().sorted().collect(StringBuilder::new,
                (sb, i) -> {
                    sb.append(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                StringBuilder::append).toString();
        System.out.println(System.nanoTime() - start);
        Assert.assertEquals(a, b);
    }
}
