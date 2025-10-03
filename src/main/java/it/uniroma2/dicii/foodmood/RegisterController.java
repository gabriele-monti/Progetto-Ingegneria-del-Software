package it.uniroma2.dicii.foodmood;

import it.uniroma2.dicii.foodmood.validator.Validator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML private TextField tfName;    
    @FXML private TextField tfSurname;  
    @FXML private TextField tfEmail;    

    @FXML private PasswordField pfPassword;        
    @FXML private TextField tfPasswordVisible;    
    @FXML private ImageView ivToggle;             

    @FXML private PasswordField pfConfirmPassword; 
    @FXML private TextField tfConfirmPassword;     
    @FXML private ImageView ivToggle1;      

    @FXML private Button btnCreateAccount;         
    @FXML private Button btnBackToLogin;           
    @FXML private Label errorMessageLabel;         

    private boolean showingPassword = false;
    private boolean showingConfirmPassword = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfPasswordVisible.textProperty().bindBidirectional(pfPassword.textProperty());
        tfConfirmPassword.textProperty().bindBidirectional(pfConfirmPassword.textProperty());

        ivToggle.setOnMouseClicked(e -> togglePasswordVisibility());
        ivToggle1.setOnMouseClicked(e -> toggleConfirmPasswordVisibility());

        btnCreateAccount.setOnAction(e -> handleRegistration());
        btnBackToLogin.setOnAction(e -> redirectToLogin());
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
            setToggleIcon(ivToggle, "/icons/eye.png");
        } else {
            pfPassword.requestFocus();
            pfPassword.positionCaret(pfPassword.getText().length());
            setToggleIcon(ivToggle, "/icons/hidden.png");
        }
    }

    private void toggleConfirmPasswordVisibility() {
        showingConfirmPassword = !showingConfirmPassword;

        tfConfirmPassword.setVisible(showingConfirmPassword);
        tfConfirmPassword.setManaged(showingConfirmPassword);

        pfConfirmPassword.setVisible(!showingConfirmPassword);
        pfConfirmPassword.setManaged(!showingConfirmPassword);

        if (showingConfirmPassword) {
            tfConfirmPassword.requestFocus();
            tfConfirmPassword.positionCaret(tfConfirmPassword.getText().length());
            setToggleIcon(ivToggle1, "/icons/eye.png");
        } else {
            pfConfirmPassword.requestFocus();
            pfConfirmPassword.positionCaret(pfConfirmPassword.getText().length());
            setToggleIcon(ivToggle1, "/icons/hidden.png");
        }
    }

    private void setToggleIcon(ImageView iv, String resourcePath) {
        URL url = getClass().getResource(resourcePath);
        if (url != null) {
            iv.setImage(new Image(url.toExternalForm()));
        }
    }

    private void handleRegistration() {
        String nome = tfName.getText().trim();
        String cognome = tfSurname.getText().trim();
        String email = tfEmail.getText().trim();
        String password = showingPassword ? tfPasswordVisible.getText() : pfPassword.getText();
        String confirmPassword = showingConfirmPassword ? tfConfirmPassword.getText() : pfConfirmPassword.getText();

        String errorMessage = validateRegistrationData(nome, cognome, email, password, confirmPassword);

        if (errorMessage != null) {
            showErrorMessage(errorMessage);
            return;
        }

        showSuccessMessage("Account creato con successo!");
        redirectToLogin();
    }

    private String validateRegistrationData(String nome, String cognome, String email,
                                            String password, String confirmPassword) {
        if (nome.isEmpty()) return "Il campo Nome è obbligatorio.";
        if (cognome.isEmpty()) return "Il campo Cognome è obbligatorio.";
        if (email.isEmpty()) return "Il campo E-mail è obbligatorio.";
        if (password.isEmpty()) return "Il campo Password è obbligatorio.";
        if (confirmPassword.isEmpty()) return "Il campo Conferma Password è obbligatorio.";
        if (!Validator.isValidEmail(email)) return "Formato e-mail non valido.";
        if (!Validator.isValidPassword(password)) return "La password deve contenere almeno 8 caratteri, una lettera maiuscola, una minuscola e un numero.";
        if (!password.equals(confirmPassword)) return "Le password non coincidono.";
        if (nome.length() < 2 || nome.length() > 50) return "Il nome deve essere compreso tra 2 e 50 caratteri.";
        if (cognome.length() < 2 || cognome.length() > 50) return "Il cognome deve essere compreso tra 2 e 50 caratteri.";
        return null;
    }

    private void redirectToLogin() {
        Navigator.setRoot("/fxml/login.fxml");
    }

    private void showErrorMessage(String message) {
        errorMessageLabel.setText(message);
        errorMessageLabel.setStyle("-fx-text-fill: #d32f2f;");
    }

    private void showSuccessMessage(String message) {
        errorMessageLabel.setText(message);
        errorMessageLabel.setStyle("-fx-text-fill: #27ae60;");
    }
}
