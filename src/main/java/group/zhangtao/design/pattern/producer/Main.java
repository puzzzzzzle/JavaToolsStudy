package group.zhangtao.design.pattern.producer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    /**
     * The default thread factory
     */
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "++working-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ResourceHandle resourceHandle = new ResourceHandle();
        int consumerCount = 1;
        int producerCount = 100;
//        BlockingDeque<Runnable> workingQueue = new LinkedBlockingQueue<>(consumerCount+producerCount);
        BlockingQueue workingQueue = new SynchronousQueue();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                4,
                consumerCount + producerCount,
                100,
                TimeUnit.SECONDS,
                workingQueue,
                new DefaultThreadFactory(),
                (runnable,threadPoolExecutor)->{
                    new Exception("pool has been full").printStackTrace();
                }
        );

        for(int i=0;i<producerCount;i++){
            Producer thread = new Producer(resourceHandle,i);
            threadPool.execute(thread);
        }
        for(int i=0;i<consumerCount;i++){
            Consumer thread = new Consumer(resourceHandle,i);
            threadPool.execute(thread);
        }

        Thread.sleep(1000*5);
        threadPool.shutdownNow();
        threadPool.awaitTermination(Integer.MAX_VALUE,TimeUnit.DAYS);
        System.out.println("end!");
    }
}
