import javafx.scene.control.ListView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8189)) {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            boolean running = true;
            Scanner scanner = new Scanner(System.in);

            Thread thread = new Thread(()-> {
                while (running) {
                    try {
                        String message = in.readUTF();
                        if ("exit".equals(message)){        
                            in.close();
                            out.close();
                            System.out.println(message);
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();

            while (running) {                           // Отключение от сервера из интерфейса клиента
                String serverExit = scanner.nextLine();
                if (serverExit.equals("exit")) {
                    out.writeUTF("exit");
                    out.flush();
                    System.out.println("Вы вышли из чата");
                    break;
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}
