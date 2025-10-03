// package it.uniroma2.dicii.foodmood.dao;

// import it.uniroma2.dicii.foodmood.exceptions.DAOException;
// import it.uniroma2.dicii.foodmood.model.Credentials;
// import it.uniroma2.dicii.foodmood.model.LoginResult;
// import it.uniroma2.dicii.foodmood.model.Role;

// import java.sql.*;

// public class LoginProcedureDAO implements P<Credentials, LoginResult> {

//     private static final String LOGIN_PROCEDURE = "{call login(?, ?, ?, ?, ?)}";

//     @Override
//     public LoginResult execute(Credentials input) throws DAOException {
//         if (input == null || input.username() == null || input.password() == null) {
//             throw new DAOException("Credenziali incomplete");
//         }

//         String username = input.username();
//         String password = input.password();

//         try (Connection conn = ConnectionFactory.getInstance().getConnection();
//              CallableStatement cs = conn.prepareCall(LOGIN_PROCEDURE)) {

//             cs.setString(1, username);
//             cs.setString(2, password);
//             cs.registerOutParameter(3, Types.INTEGER);
//             cs.registerOutParameter(4, Types.INTEGER);
//             cs.registerOutParameter(5, Types.VARCHAR);

//             cs.execute();

//             int roleValue = cs.getInt(3);
//             int errorCode = cs.getInt(4);
//             String errorMessage = cs.getString(5);

//             Role role = Role.fromInt(roleValue);

//             return new LoginResult(
//                     new Credentials(username, null, role),
//                     errorCode,
//                     errorMessage
//             );

//         } catch (SQLException e) {
//             throw new DAOException("Errore durante l'esecuzione della procedura di login", e);
//         }
//     }
// }
