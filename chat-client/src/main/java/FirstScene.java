
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FirstScene {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authorizationChat;

    @FXML
    private Button registrationChat;

    @FXML
    private TextField passwordChat;

    @FXML
    private TextField loginChat;

    @FXML
    void initialize() {
        authorizationChat.setOnAction(event -> {
            String loginText = loginChat.getText().trim();
            String passwordText = passwordChat.getText().trim();
            if (!loginText.equals("") && passwordText.equals("")) {
                loginUser(loginText, passwordText);
            } else {
                System.out.println("Введите Имя пользователя и пароль");
            }
        });


        registrationChat.setOnAction(event -> {
            registrationChat.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("registrationScene.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void loginUser(String loginText, String passwordText) {
    }
}
