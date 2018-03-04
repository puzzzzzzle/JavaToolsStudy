package group.zhangtao.design.pattern.single;

import static java.lang.Thread.sleep;

public class StaticHolder {

    private StaticHolder() {
        System.out.println("static holder init:, time:"+System.nanoTime());
    }
    public static StaticHolder getInstance() {
        return Holder.INSTANCE;
    }
    private static class Holder {
        private static StaticHolder INSTANCE = new StaticHolder();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start time"+System.nanoTime());
        sleep(1000);
//        for (int i = 0; i < 50; i++) {
//            new Thread(() -> System.out.println(StaticHolder.getInstance().toString())).start();
//        }
        WorkThread.testRun(() -> System.out.println(StaticHolder.getInstance().toString()));
    }
}
