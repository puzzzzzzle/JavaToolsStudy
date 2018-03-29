package group.zhangtao.mvolatile;

import java.util.concurrent.CountDownLatch;

public class Main {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) throws InterruptedException {
        final Main test = new Main();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i=0;i<10;i++){
            new Thread(() -> {
                for(int j=0;j<10000;j++)
                    test.increase();
                countDownLatch.countDown();
                System.out.println("m:"+test.inc);
            }).start();
        }
        countDownLatch.await();
        System.out.println(test.inc);
    }
}
