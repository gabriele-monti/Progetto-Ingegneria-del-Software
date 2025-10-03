// package it.uniroma2.dicii.foodmood.config;

// import it.uniroma2.dicii.foodmood.dao.ConnectionFactory;

// import java.sql.Connection;
// import java.sql.SQLException;

// public final class DatabaseInitializer {

//     private DatabaseInitializer() {
//     }

//     public static void verificaConnessioneDB() throws SQLException {
//         Connection conn = ConnectionFactory.getInstance().getConnection();
//         boolean valida = conn.isValid(5);
//         if (!valida) {
//             throw new SQLException("Connessione al database non valida");
//         }
//     }
// }
