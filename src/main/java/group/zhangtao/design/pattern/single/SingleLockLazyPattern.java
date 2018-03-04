package group.zhangtao.design.pattern.single;

import static java.lang.Thread.sleep;

public class SingleLockLazyPattern {
    private SingleLockLazyPattern() {
        System.out.println("Safe lazy Pattern init, time:" + System.nanoTime());
    }

    private static SingleLockLazyPattern instance = null;

    public synchronized static SingleLockLazyPattern getInstance() {
        if (instance == null) {
            instance = new SingleLockLazyPattern();
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start time" + System.nanoTime());

        sleep(1000);
//        for (int i = 0; i < 50; i++) {
//            new Thread(() -> System.out.println(SingleLockLazyPattern.getInstance().toString())).start();
//        }
        WorkThread.testRun(() -> {
            System.out.println(SingleLockLazyPattern.getInstance().toString());
            try {
                sleep(WorkThread.sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
