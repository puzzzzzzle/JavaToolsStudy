package group.zhangtao.grammer.thread;

import java.util.concurrent.*;

public class Pool {

    private ThreadPoolExecutor executor;

    public static void main(String[] args) throws InterruptedException {
        Pool pool = new Pool();
        pool.linkedPoolTest(30, 500);
        Thread.sleep(7000);
        System.out.println("++++interrupt++++++++");
        pool.shutDownNow();
//        pool.arrayPoolTest(5000);
    }

    private void runAdd(ThreadPoolExecutor executor) {
        while (!executor.isTerminated()) {
            int j = 0;
            System.out.println("task count:" + executor.getTaskCount());
            System.out.println("queue count:" + executor.getQueue().size());
            System.out.println("active count" + executor.getActiveCount());
            System.out.println("max count" + executor.getLargestPoolSize());
//            System.out.println("is active"+executor.getQueue());
//            executor.execute(new Thread(new WorkThread("append:  " + String.valueOf(++j))));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Pool thread interrupted");
            }
        }
    }

    private void linkedPoolTest(int maxThread, int queueSize) {
        LinkedBlockingDeque<Runnable> workQueue = null;
        if (queueSize <= 0)
            workQueue = new LinkedBlockingDeque<>();
        else
            workQueue = new LinkedBlockingDeque<>(queueSize);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 3, TimeUnit.SECONDS, workQueue, new ThreadPoolExecutor.AbortPolicy());
        this.executor = executor;
        try {

            for (int i = 0; i < maxThread; i++) {
                executor.execute(new Thread(new WorkThread(String.valueOf(i))));
            }
//            runAdd(executor);
        } catch (RejectedExecutionException e) {
            System.out.println("reject");
        }
//        runAdd(executor);
    }

    private void arrayPoolTest(int maxThread) {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(3000);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 100, 3, TimeUnit.SECONDS, workQueue, new ThreadPoolExecutor.AbortPolicy());
        this.executor = executor;
        try {

            for (int i = 0; i < maxThread; i++) {
                executor.execute(new Thread(new WorkThread(String.valueOf(i))));
            }
//            runAdd(executor);
        } catch (RejectedExecutionException e) {
            System.out.println("reject");
        }
        runAdd(executor);
    }

    private void shutDownNow() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }
}
