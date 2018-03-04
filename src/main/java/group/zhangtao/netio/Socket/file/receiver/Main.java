package group.zhangtao.netio.Socket.file.receiver;

public class Main {
    public static void main(String[] args) {
        ServiceThreadPool serverThread = ServiceThreadPool.getServerThread(8189, "./receive");
        new Thread(serverThread).start();
    }
}
