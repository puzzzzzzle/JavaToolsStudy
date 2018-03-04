package group.zhangtao.grammer.thread;

public class WorkThread implements Runnable {
    private int runTimes = 0;
    private String name = "";

    public WorkThread(String name) {
//        Thread.currentThread().setName(name);
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted() && runTimes < 3) {
                //模仿执行时间
                Thread.sleep(3000);
                System.out.println(name + ":" + Thread.currentThread().getName() + "count:" + runTimes);
                runTimes++;
            }
        } catch (InterruptedException e) {

            //中断时结束线程逻辑
            System.out.println("try stop in sleep");
            return;
//            e.printStackTrace();
        }
        System.out.println();
    }
}
