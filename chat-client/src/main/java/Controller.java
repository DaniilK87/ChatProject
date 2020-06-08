package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sun.security.mscapi.CPublicKey;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Controller {

    public TextArea textArea;
    public TextField textField;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    boolean running = true;

    // отправка сообщения при нажатии кнопки "Отправить сообщение"
    public void sendMessage(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        textArea.appendText(textField.getText() + System.lineSeparator());
        textField.clear();
        textArea.setWrapText(true);
        button.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    button.getText();
                    textField.appendText(button.getText() + System.lineSeparator());
                    textField.clear();

                }
            }
        });
    }

    public void keyListener(KeyEvent keyEvent) {
        System.out.println(keyEvent.getText());
    }


    public void inputText(KeyEvent keyEvent) {
        textArea.setWrapText(true);
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    textArea.appendText(textField.getText() + System.lineSeparator());
                    textField.clear();

                }
            }
        });
    }

    // соединение с сервером и отправка сообщений
    public void connectServer(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        socket = new Socket("localhost", 8189);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        textArea.appendText("Вы подключились" + System.lineSeparator());
        Thread thread = new Thread(() -> {
            while (running) {
                try {
                    String message = in.readUTF();
                    if ("exit".equals(message)) {
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
        button.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.E) {
                    button.getOnKeyPressed();
                }
            }
        });
    }

    // Отключение от сервера из интерфейса клиента
    public void disconnectServer(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        textArea.appendText("Вы покинули чат" + System.lineSeparator());
        String message = "exit";
        while (running) {
                out.writeUTF(message);
                out.flush();
                break;
            }
        button.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.E) {
                        button.getOnKeyPressed();
                    }
            }
        });

    }
}






