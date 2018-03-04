package group.zhangtao.netio.Socket.anotherStudy;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public final class SocketServerTest implements Runnable {
    private final Socket socket;

    public SocketServerTest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                Scanner scanner = new Scanner(in);
                PrintWriter writer = new PrintWriter(out)

        ) {
            while (!Thread.currentThread().isInterrupted() && scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                if (temp.toUpperCase().trim().equals("BYE")) {
                    break;
                }
                String echo = "echo:" + temp;
                System.out.println(echo);
                out.write(echo.trim().getBytes());
//                out.write("".getBytes());
//                writer.print(echo);

            }
            System.out.println("++END++");
            if (socket != null)
                socket.close();
            {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
