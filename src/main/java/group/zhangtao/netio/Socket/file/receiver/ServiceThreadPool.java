package group.zhangtao.netio.Socket.file.receiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 单例模式的线程池socket服务器
 */
public final class ServiceThreadPool implements Runnable {
    private int port = 8189;
    private ServerSocket serverSocket = null;
    private String saveRootPath = "./";

    private ServiceThreadPool(int mPort) {
        port = mPort;
    }

    private ServiceThreadPool() {
        port = 8189;
    }

    public static ServiceThreadPool getServerThread(int port, String saveRootPath) {
        Holder.INSTANCE.setPort(port).setSaveRootPath(saveRootPath);
        return Holder.INSTANCE;
    }

    public static ServiceThreadPool getServerThread(int port) {
        return Holder.INSTANCE.setPort(port);
    }

    public static ServiceThreadPool getServerThread() {
        return Holder.INSTANCE.init();
    }

    public String getSaveRootPath() {
        return saveRootPath;
    }

    public void setSaveRootPath(String saveRootPath) {
        this.saveRootPath = saveRootPath;
    }

    public int getPort() {
        return port;
    }

    public ServiceThreadPool setPort(int port) {
        this.port = port;
        return Holder.INSTANCE.init();
    }

    public ServiceThreadPool init() {
        this.serverSocket = null;
        while (this.serverSocket == null) {
            try {
                this.serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                System.out.println("端口已被占用：" + port + "自动加一");
                serverSocket = null;
                port++;
            }
        }
        System.out.println(port);
        return Holder.INSTANCE;
    }

    @Override
    public void run() {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(1000);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 3, TimeUnit.MINUTES, workQueue);
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                //接到连接后处理的线程
                Thread service = new Thread(new FileReceiverSocket(socket, saveRootPath));
                threadPoolExecutor.execute(service);
            } catch (IOException e) {
                serverSocket = null;
                return;
            }
        }
    }

    private static class Holder {
        private static ServiceThreadPool INSTANCE = new ServiceThreadPool();
    }
}
