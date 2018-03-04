package group.zhangtao.design.pattern.productor;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Consumer implements Runnable ,StopController{
    boolean isStop = false;
    private final ResourceHandle resourceHandle;
    private int id;
    public Consumer(ResourceHandle resourceHandle,int id){
        this.resourceHandle=resourceHandle;
        this.id=id;
    }
    private void useProduct() throws InterruptedException {
        Thread.sleep(resourceHandle.consumerTime);
    }
    //避免多个消费者同时take
    private synchronized Product take() throws FileNotFoundException {
        if(!resourceHandle.isEmpty()){
            return resourceHandle.take(id);
        }else {
            new Exception("原子性错误！").printStackTrace();
            try(PrintStream printStream = new PrintStream(
                    new BufferedOutputStream(
                            new FileOutputStream("productor wrong.log")
                    )
            )){
                printStream.println("原子性错误！");
            }
            return null;
        }
    }
    private void consumerAction() throws InterruptedException, FileNotFoundException {
        useProduct();
        if(resourceHandle.isEmpty()){
            synchronized (resourceHandle.producerMonitor){
                resourceHandle.producerMonitor.notifyAll();
            }
            synchronized (resourceHandle.customerMonitor){
                System.out.println(String.format("Empty ： %d wait",id));
                resourceHandle.customerMonitor.wait();
            }
        }else {
            Product product = take();
            if(product!=null){
                //todo : 不足
                System.out.println("product is :"+product.getUuid());
            }
        }
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()&&!isStop){
            try {
                consumerAction();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        isStop=true;
    }
}
