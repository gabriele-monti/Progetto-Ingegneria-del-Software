// package it.uniroma2.dicii.foodmood.controller;

// import it.uniroma2.dicii.foodmood.model.Credentials;
// import it.uniroma2.dicii.foodmood.model.LoginResult;
// import it.uniroma2.dicii.foodmood.config.DatabaseInitializer;
// import it.uniroma2.dicii.foodmood.dao.LoginProcedureDAO;

// import java.sql.SQLException;

// public class LoginController {

//     private final LoginProcedureDAO loginProcedureDAO;
//     private Credentials credenziali;

//     public LoginController(LoginProcedureDAO loginProcedureDAO) {
//         this.loginProcedureDAO = loginProcedureDAO;
//     }

//     public void start() {
//         try {
//             credenziali = view.authenticate();
//             if (credenziali == null) {
//                 view.mostraErrore("Autenticazione annullata.");
//                 return;
//             }

//             LoginResult loginResult = loginProcedureDAO.execute(credenziali);

//             if (!loginResult.isSuccessful()) {
//                 credenziali = null; 
//                 view.mostraErrore(loginResult.mostraMessaggioErrore());
//             } else {
//                 credenziali = loginResult.credenziali();
//             }

//         } catch (Exception e) {
//             view.mostraErrore("Errore durante il login: " + e.getMessage());
//         }
//     }

//     public Credentials ottieniCredenziali() {
//         return credenziali;
//     }

//     public boolean verificaConnessione() {
//         try {
//             DatabaseInitializer.verificaConnessioneDB();
//             return true;
//         } catch (SQLException e) {
//             view.mostraErrore("Connessione al database persa: " + e.getMessage());
//             return false;
//         }
//     }
// }
