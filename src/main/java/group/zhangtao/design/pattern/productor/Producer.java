package group.zhangtao.design.pattern.productor;

import java.util.UUID;

public class Producer implements Runnable,StopController{
    private boolean isStop=false;
    private final ResourceHandle resourceHandle;
    private int id;
    public Producer(ResourceHandle resourceHandle, int id){
        this.resourceHandle=resourceHandle;
        this.id=id;
    }
    private Product makeProduct() throws InterruptedException {
        Thread.sleep(resourceHandle.productTime);
        return new Product(UUID.randomUUID());
    }
    private void produce() throws InterruptedException {
        //先生产，再加入，加快速度
        Product product = makeProduct();

        if(resourceHandle.isFull()){
            //死锁保险
            synchronized (resourceHandle.customerMonitor){
                resourceHandle.customerMonitor.notifyAll();
            }
            synchronized (resourceHandle.producerMonitor){
                System.out.println(String.format("Full : %d wait",id));
                resourceHandle.producerMonitor.wait();
            }
        }else {
            //todo : 超出
            resourceHandle.add(product,id);
            synchronized (resourceHandle.customerMonitor){
                resourceHandle.customerMonitor.notify();
            }
        }
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()&&!isStop){
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        isStop=true;
    }
}
