package group.zhangtao.design.pattern.single;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Thread.sleep;

public class WorkThread implements Runnable {
    public static int sleepTime =100;

    private Runnable runnable;
    private CountDownLatch countDownLatch;
    private static AtomicLong startTime = new AtomicLong();
    private static AtomicLong finishTime = new AtomicLong();

    private WorkThread(Runnable testRunnable, CountDownLatch countDownLatch) {
        this.runnable = testRunnable;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        runnable.run();
        countDownLatch.countDown();
        System.out.println("finish");
        if (countDownLatch.getCount() == 0) {
            finishTime.set(System.nanoTime());
            System.out.println("finish time:" + finishTime);
            System.out.println("all time:" + String.valueOf((finishTime.get()-startTime.get())));

        }
    }

    public static void testRun(Runnable runnable) {
        int times =50;
        CountDownLatch countDownLatch = new CountDownLatch(times);
        long time = System.nanoTime();
        WorkThread.startTime.set(time);
        System.out.println("start time"+String.valueOf(time));
        for (int i = 0; i < times; i++) {
            new Thread(new WorkThread(runnable, countDownLatch)).start();
        }
    }

    public static void main(String[] args) {
        testRun(() -> {
            System.out.println("start");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
