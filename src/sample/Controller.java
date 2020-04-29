package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

    public TextArea textArea;
    public TextField textField;
    String text1;
    boolean x;



    public void sendMessage(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        textArea.appendText(textField.getText());
        textField.clear();
        textArea.setWrapText(true);
        button.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    button.getText();
                    textField.appendText(button.getText());
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
                    textArea.appendText(textField.getText());
                    textField.clear();

                }
            }
        });
    }


}




///////////