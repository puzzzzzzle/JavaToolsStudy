package group.zhangtao.netio.Socket.file.translater;

public class Main {
    public static void main(String[] args) {
        new Thread(new Sender("./中文测试.zip", "127.0.0.1", 8189)).start();
    }
}
