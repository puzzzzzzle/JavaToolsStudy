package group.zhangtao.design.pattern.single;

import static java.lang.Thread.sleep;

public class HungryPattern {
    private HungryPattern(){
        System.out.println("Hungry pattern init, time:"+System.nanoTime());
    }
    private static HungryPattern instance = new HungryPattern();
    public static HungryPattern getInstance(){
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start time"+System.nanoTime());

        sleep(1000);
//        for (int i = 0; i < 50; i++) {
//            new Thread(() -> System.out.println(HungryPattern.getInstance().toString())).start();
//        }
        WorkThread.testRun(() -> System.out.println(HungryPattern.getInstance().toString()));
    }
}
