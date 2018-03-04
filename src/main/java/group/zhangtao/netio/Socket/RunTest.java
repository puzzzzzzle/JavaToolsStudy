package group.zhangtao.netio.Socket;

import java.util.concurrent.TimeUnit;

public class RunTest implements Runnable {
    private int i;

    public RunTest(int in) {
        i = in;
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunTest(1));
        Thread thread2 = new Thread(new RunTest(2));
        Thread thread3 = new Thread(new RunTest(3));
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println("all thread start");
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("run" + i);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
