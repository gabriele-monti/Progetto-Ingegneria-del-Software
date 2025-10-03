package it.uniroma2.dicii.foodmood;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class Navigator {
    private static Stage stage;
    private static Scene scene;

    private Navigator() {}

    public static void init(Stage s, Scene sc) {
        stage = s;
        scene = sc;
    }

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setRoot(String fxmlAbsolutePath) {
        try {
            Parent root = FXMLLoader.load(Navigator.class.getResource(fxmlAbsolutePath));
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
