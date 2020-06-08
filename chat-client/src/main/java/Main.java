

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
        Parent firstScene = FXMLLoader.load(getClass().getResource("firstScene.fxml"));
        //Parent secondScene = FXMLLoader.load(getClass().getResource("chatScene.fxml"));
        primaryStage.setTitle("Chat Project v.1.1");
        primaryStage.setScene(new Scene(firstScene, 480, 620));
        //primaryStage.setScene(new Scene(secondScene, 480, 620));
        primaryStage.show();} catch (Exception e) {e.printStackTrace();}
    }


    public static void main(String[] args) {
        launch(args);

    }
}

