package group.zhangtao.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinMain {
    public static void test(int numbers, int workTime) throws ExecutionException, InterruptedException {
        System.out.println(String.format("start test, numbers:%d,sleepTime:%d", numbers, workTime));
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < numbers; i++) {
            integers.add((int) (Math.random() * 10000));
//            integers.add(i);

        }
        long startTime = System.nanoTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() - 1);

        ForkJoinTask<Integer> result = forkJoinPool.submit(new MForkJoinTask(0, integers.size(), integers, workTime));

        Integer sum = result.get();
        long totalTime = System.nanoTime() - startTime;
        System.out.println(String.format("fork/join:result:%d, time:%d", sum, totalTime));

        startTime = System.nanoTime();

        Integer sum1 = 0;
        for (int i = 0; i < integers.size(); i++) {
            if (workTime > 0) {
                Thread.sleep(workTime);
            }
            sum1 += integers.get(i);
        }
        long totalTime1 = System.nanoTime() - startTime;
        System.out.println(String.format("for:result:%d, time:%d", sum1, totalTime1));
        System.out.println(String.format("fork join time - for time:%d\n", totalTime - totalTime1));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test(100, 0);
        test(1000000, 0);

        test(100, 1);
        test(100, 10);

    }
}
