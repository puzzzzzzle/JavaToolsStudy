package group.zhangtao.netio.Socket.file.translater;

import java.io.*;
import java.net.Socket;

public class Sender implements Runnable {
    private final int BUFFER_SIZE = 1024;
    private String sourcePath = "";
    private String host = "";
    private int port = 0;
    private byte[] buf = new byte[BUFFER_SIZE];
    public Sender(String sourcePath, String host, int port) {
        this.sourcePath = sourcePath;
        this.host = host;
        this.port = port;
    }

    private void send(String path, String host, int port) {
        try (
                Socket socket = new Socket(host, port);
                DataInputStream in = new DataInputStream(
                        new BufferedInputStream(
                                new FileInputStream(path)
                        )
                );
                DataOutputStream out = new DataOutputStream(
                        new BufferedOutputStream(
                                socket.getOutputStream()
                        )
                )
        ) {
            out.writeUTF(new File(path).getName());
            while (!Thread.currentThread().isInterrupted()
                    && in.read(buf) != -1) {
                out.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        send(sourcePath, host, port);
    }
}
