package group.zhangtao.design.pattern.single;

import static java.lang.Thread.sleep;

public class DoubleLockSafeLazyPattern {
    private DoubleLockSafeLazyPattern() {
        System.out.println("Safe lazy Pattern init, time:" + System.nanoTime());
    }

    private static DoubleLockSafeLazyPattern instance = null;


    public static DoubleLockSafeLazyPattern getInstanceOther() {
        if (instance == null) {
            synchronized (DoubleLockSafeLazyPattern.class) {
                if (instance == null) {
                    instance = new DoubleLockSafeLazyPattern();
                }
            }
        }
        return instance;
    }

    /**
     * 通过static object 更改
     */
    private static Object object = new Object();

    public static DoubleLockSafeLazyPattern getInstanceAnother() {
        if (instance == null) {
            synchronized (object) {
                if (instance == null) {
                    instance = new DoubleLockSafeLazyPattern();
                }
            }
        }
        return instance;
    }

    /**
     * 貌似有错
     *
     * @return
     */
    public static DoubleLockSafeLazyPattern getInstanceWrong() {
        if (instance == null) {
            // wrong: instance 开始时是空的不能做到延迟初始化
            synchronized (instance) {
                if (instance == null) {
                    instance = new DoubleLockSafeLazyPattern();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start time" + System.nanoTime());

        sleep(1000);
//        for (int i = 0; i < 50; i++) {
//            new Thread(() -> System.out.println(DoubleLockSafeLazyPattern.getInstanceOther().toString())).start();
//        }
        WorkThread.testRun(() -> {
            System.out.println(DoubleLockSafeLazyPattern.getInstanceOther().toString());
            try {
                sleep(WorkThread.sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
