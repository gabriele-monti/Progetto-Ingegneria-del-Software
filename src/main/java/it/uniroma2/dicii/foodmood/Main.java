package it.uniroma2.dicii.foodmood;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final String USERNAME = "abc";
    public static final String PASSWORD = "123";

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("FoodMood - Login");
        stage.setScene(scene);
        stage.show();

        Navigator.init(stage, scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
