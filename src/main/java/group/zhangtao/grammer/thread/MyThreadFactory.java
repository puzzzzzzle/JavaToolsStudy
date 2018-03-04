package group.zhangtao.grammer.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {
    private AtomicInteger count = null;
    private Runnable runnable = null;

    public MyThreadFactory() {
        count = new AtomicInteger();
        count.set(0);
    }

    @Override
    public Thread newThread(Runnable r) {
        count.set(count.get() + 1);
        return new Thread(r);
    }
}
