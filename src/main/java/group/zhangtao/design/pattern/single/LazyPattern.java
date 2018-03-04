package group.zhangtao.design.pattern.single;

import static java.lang.Thread.sleep;

public class LazyPattern {
    private LazyPattern() {
        System.out.println("Lazy Pattern init, time:" + System.nanoTime());
    }

    private static LazyPattern instance = null;

    public static LazyPattern getInstance() {
        if (instance == null) {
            instance = new LazyPattern();
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start time" + System.nanoTime());

        sleep(1000);
//        for (int i = 0; i < 50; i++) {
//            new Thread(() -> System.out.println(LazyPattern.getInstance().toString())).start();
//        }
        WorkThread.testRun(() -> {
            System.out.println(LazyPattern.getInstance().toString());
            try {
                sleep(WorkThread.sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
