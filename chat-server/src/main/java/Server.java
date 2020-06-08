import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Server {
    private final static int PORT = 8189;
    private final static String HOST = "localhost";
    private boolean running;
    public int cnt = 1;


    private static ConcurrentLinkedDeque<ClientHandler> clients;


    public Server (int PORT)  {
        running = true;
        clients = new ConcurrentLinkedDeque<>();
       try (ServerSocket server = new ServerSocket(PORT)) {
           System.out.println("Server START");
            while (running) {
                Socket socket = server.accept();
                ClientHandler client = new ClientHandler(socket,"client" + cnt);
                cnt++;
                clients.add(client);
                System.out.println(client.getNickName() + " " + "подключился");
                new Thread(client).start();
            }
       } catch (Exception e) {e.printStackTrace();}

    }



    public static void main(String[] args)  {
        Server server = new Server(PORT);
    }

    public static ConcurrentLinkedDeque<ClientHandler> getClients() {
        return clients;
    }

}

