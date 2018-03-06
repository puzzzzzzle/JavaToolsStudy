package group.zhangtao.design.pattern.producer;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Consumer implements Runnable {
    private final ResourceHandle resourceHandle;
    private int id;

    public Consumer(ResourceHandle resourceHandle, int id) {
        this.resourceHandle = resourceHandle;
        this.id = id;
    }

    private void useProduct(Product product) throws InterruptedException {
        if (product != null) {
            Thread.sleep(resourceHandle.consumerTime);

            System.out.println("product is :" + product.getUuid());
        }
    }

    //避免多个消费者同时take
    private synchronized Product take() throws FileNotFoundException {
        if (!resourceHandle.isEmpty()) {
            return resourceHandle.take(id);
        } else {
            new Exception("原子性错误！").printStackTrace();
            try (PrintStream printStream = new PrintStream(
                    new BufferedOutputStream(
                            new FileOutputStream("producer wrong.log")
                    )
            )) {
                printStream.println("原子性错误！");
            }
            return null;
        }
    }

    private void consumerAction() throws InterruptedException, FileNotFoundException {
        Product product = null;
        if (resourceHandle.isEmpty()) {
            synchronized (resourceHandle.producerMonitor) {
                resourceHandle.producerMonitor.notifyAll();
            }
            synchronized (resourceHandle.customerMonitor) {
                System.out.println(String.format("Empty ： %d wait", id));
                resourceHandle.customerMonitor.wait();
            }
        } else {
            product = take();
            useProduct(product);

        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                consumerAction();
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            System.out.println("consumer " + id + " finish!");
//            synchronized (resourceHandle.customerMonitor) {
//                resourceHandle.customerMonitor.notifyAll();
//            }
//            synchronized (resourceHandle.producerMonitor) {
//                resourceHandle.producerMonitor.notifyAll();
//            }
        }
    }
}
