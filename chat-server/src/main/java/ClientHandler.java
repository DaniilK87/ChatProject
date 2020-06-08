import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String nickName;
    public boolean running;

    public ClientHandler (Socket socket, String nickName) throws IOException {
        this.nickName = nickName;
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        running = true;
        welcome();
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void welcome () throws IOException {         // приветствие при соединении в консоли Клиента
        out.writeUTF("Привет" + " " + nickName);
        out.flush();
    }


    public void broadCastMessage (String message) throws IOException {
        for (ClientHandler client: Server.getClients()) {
            if (client.equals(this)) {
                client.sendMessage(message);
            }
        }
    }

    public void sendMessage (String message) throws IOException {
        out.writeUTF(message);
        out.flush();

    }

    @Override
    public void run() {
        while (running) {
            try {
                if (socket.isConnected()) {                 // читает сообщения от клиента, если приходит "exit" отключает клиента от сервера
                    String clientMessage = in.readUTF();
                    if(clientMessage.equals("exit")) {
                        Server.getClients().remove(this);
                        System.out.println(clientMessage + " " + nickName);
                        break;
                    }
                    broadCastMessage(clientMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /*public void sendMessageToClient (ClientHandler from, String nickName, String message) throws IOException {
        for (ClientHandler o: Server.getClients()) {
            if (o.getNickName().equals(nickName)) {
                o.sendMessage("от " + " " + from.getNickName() + " " + ": " + message);
                from.sendMessage("клиенту" + " " + nickName + " " + message);
                return;
            }
        }
        from.sendMessage("Участника" + " " + nickName + " " + "нет в чате");
    }

    public  void broadCastClients () throws IOException {
        StringBuilder sb = new StringBuilder("/clients");
        for (ClientHandler o: Server.getClients()) {
            sb.append(o.getNickName() + " ");
        }
        broadCastMessage(sb.toString());
    }*/
}
