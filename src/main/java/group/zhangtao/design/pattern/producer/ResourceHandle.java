package group.zhangtao.design.pattern.producer;

import java.util.LinkedList;
import java.util.Queue;

public class ResourceHandle {
    private final int capacity = 50;
    public int getCapacity() {
        return capacity;
    }

    public Queue<Product> products = new LinkedList<>();

    public final Object producerMonitor = new Object();
    public final Object customerMonitor = new Object();

    public int productTime = 50;
    public int consumerTime = 5;

    public synchronized boolean isFull(){
        return products.size()>=capacity;
    }
    public synchronized boolean isEmpty(){
        return products.isEmpty();
    }
    public synchronized boolean add(Product product) {
        return products.offer(product);
    }
    public synchronized boolean add(Product product,int makerId) {
        System.out.println(String.format("make : %d make a product,now is :%d",makerId,products.size()+1));
        return products.offer(product);
    }
    public synchronized Product take(){
        return products.poll();
    }
    public synchronized Product take(int takerId){
        System.out.println(String.format("take : %d take a product,now is :%d",takerId,products.size()-1));
        return products.poll();
    }
}
