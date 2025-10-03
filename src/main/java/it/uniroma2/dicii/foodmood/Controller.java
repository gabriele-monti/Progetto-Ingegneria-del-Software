package it.uniroma2.dicii.foodmood;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Button btnLogin;
    @FXML private Button btnCreateAccount;

    @FXML private PasswordField pfPassword;
    @FXML private TextField tfEmail;

    @FXML private Label errorMessageLabel;

    @FXML private TextField tfPasswordVisible;
    @FXML private ImageView ivToggle;

    private boolean showingPassword = false;
    private String errorMessage = "";

    private boolean isFieldFilled(){
        boolean isFilled = true;
        String pwd = showingPassword ? tfPasswordVisible.getText() : pfPassword.getText();

        if (tfEmail.getText() == null || tfEmail.getText().isBlank() ||
            pwd == null || pwd.isBlank()) {
            isFilled = false;
            errorMessage = "Credenziali incomplete";
            errorMessageLabel.setText(errorMessage);
        } else {
            errorMessage = "";
            errorMessageLabel.setText(errorMessage);
        }
        return isFilled;
    }

    private boolean isValid(){
        boolean ok = true;
        String pwd = showingPassword ? tfPasswordVisible.getText() : pfPassword.getText();
        StringBuilder sb = new StringBuilder();

        if (!String.valueOf(tfEmail.getText()).equals(Main.USERNAME) ||
            !String.valueOf(pwd).equals(Main.PASSWORD)) {
            ok = false;
            sb.append("Credenziali non valide!");
        }
        errorMessage = sb.toString();
        errorMessageLabel.setText(errorMessage);
        return ok;
    }

    private void goTo(String fxml) {
        Navigator.setRoot(fxml); 
    }

    private void startHomeWindow(){
        goTo("/fxml/homeClient.fxml");
    }

    private void startCreateAccountWindow(){
        goTo("/fxml/register.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (btnLogin != null) {
            btnLogin.setDefaultButton(true);
            btnLogin.setOnAction(e -> {
                errorMessageLabel.setText("");
                errorMessage = "";
                if (isFieldFilled() && isValid()) {
                    startHomeWindow();
                }
            });
        }

        if (btnCreateAccount != null) {
            btnCreateAccount.setOnAction(e -> {
                errorMessageLabel.setText("");
                errorMessage = "";
                startCreateAccountWindow();
            });
        }

        if (tfPasswordVisible != null && pfPassword != null) {
            tfPasswordVisible.textProperty().bindBidirectional(pfPassword.textProperty());
        }

        if (ivToggle != null) {
            ivToggle.setOnMouseClicked(e -> togglePasswordVisibility());
        }

        tfEmail.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(ke -> {
                    if (ke.getCode() == KeyCode.ESCAPE) {
                        System.exit(0);
                    }
                });
                tfEmail.requestFocus();
            }
        });
    }

    private void togglePasswordVisibility() {
        showingPassword = !showingPassword;

        tfPasswordVisible.setVisible(showingPassword);
        tfPasswordVisible.setManaged(showingPassword);

        pfPassword.setVisible(!showingPassword);
        pfPassword.setManaged(!showingPassword);

        if (showingPassword) {
            tfPasswordVisible.requestFocus();
            tfPasswordVisible.positionCaret(tfPasswordVisible.getText().length());
            setToggleIcon("/icons/eye.png");
        } else {
            pfPassword.requestFocus();
            pfPassword.positionCaret(pfPassword.getText().length());
            setToggleIcon("/icons/hidden.png");
        }
    }

    private void setToggleIcon(String resourcePath) {
        URL url = getClass().getResource(resourcePath);
        if (url != null) {
            ivToggle.setImage(new Image(url.toExternalForm()));
        }
    }
}
