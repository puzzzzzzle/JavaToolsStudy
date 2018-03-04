package group.zhangtao.netio.Socket.anotherStudy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {
    private static ServerThread serverThread = null;
    private int port = 8189;
    private ServerSocket serverSocket = null;

    private ServerThread() {
        while (this.serverSocket == null) {
            try {
                this.serverSocket = new ServerSocket(port);
            } catch (IOException e) {
//            e.printStackTrace();
                System.out.println("端口已被占用：" + port + "自动加一");
                serverSocket = null;
                port++;
            }
        }

    }

    public static ServerThread getServerSocket() {
        if (serverThread == null) {
            serverThread = new ServerThread();
        }
        return serverThread;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Server start at"+port);
                Thread service = new Thread(new SocketServerTest(socket));
                service.start();
            } catch (IOException e) {
                serverSocket = null;
                return;
            }
        }
    }
}
