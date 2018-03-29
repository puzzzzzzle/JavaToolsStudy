package group.zhangtao.lock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        StringBuilder o = new StringBuilder("0");
        new Thread(new RunTask(o)).start();
        new Thread(new RunTask(o)).start();
        new Thread(new RunTask(o)).start();

        Thread.sleep(3000);
        synchronized (o) {
            o.deleteCharAt(0);
            o.append(1);
            o.notifyAll();
        }
    }
}

class RunTask implements Runnable {
    private StringBuilder lock;

    public RunTask(StringBuilder lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println(String.format("%s run before", lock.toString()));
        synchronized (lock) {
            try {
                System.out.println(String.format("%s running wait lock", lock.toString()));
                lock.wait();
                System.out.println(String.format("%s running finish", lock.toString()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("%s run after", lock.toString()));
    }
}
