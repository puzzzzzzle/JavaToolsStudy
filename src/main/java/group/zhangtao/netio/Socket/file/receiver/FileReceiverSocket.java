package group.zhangtao.netio.Socket.file.receiver;

import java.io.*;
import java.net.Socket;

public class FileReceiverSocket implements Runnable {
    private final int BUFFER_SIZE = 1024;
    private Socket socket = null;
    private String savePath = "";

    public FileReceiverSocket(Socket socket, String savePath) {
        this.socket = socket;
        this.savePath = savePath;
    }

    @Override
    public void run() {
        try (
                DataInputStream in = new DataInputStream(new BufferedInputStream(
                        socket.getInputStream())
                );
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
                        new FileOutputStream(savePath + File.separator + in.readUTF()))
                )
        ) {
            byte[] buf = new byte[BUFFER_SIZE];
            while (!Thread.currentThread().isInterrupted()
                    && in.read(buf) != -1) {
                out.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
