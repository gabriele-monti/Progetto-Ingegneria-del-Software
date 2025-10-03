// package it.uniroma2.dicii.foodmood.dao;

// import it.uniroma2.dicii.foodmood.model.Role;

// import java.sql.*;
// import java.util.Properties;
// import java.util.logging.Level;
// import java.util.logging.Logger;


// public class ConnectionFactory {
//     private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

//     private static ConnectionFactory instance;
//     private final Properties properties;
//     private final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

//     private ConnectionFactory() {
//         this.properties = PropertiesLoader.getProperties();
//         try {
//             Class.forName("it.mysql.cj.jdbc.Driver");
//         } catch (ClassNotFoundException e) {
//             throw new RuntimeException("Driver MySQL non trovato", e);
//         }
//     }

//     public static synchronized ConnectionFactory getInstance() {
//         if (instance == null) {
//             instance = new ConnectionFactory();
//         }
//         return instance;
//     }

//     public Connection getConnection() throws SQLException {
//         Connection conn = connectionThreadLocal.get();

//         if (conn == null || conn.isClosed()) {
//             conn = createConnection("LOGIN_USER", "LOGIN_PASS");
//             connectionThreadLocal.set(conn);
//         }

//         return conn;
//     }

//     public void changeRole(Role role) throws SQLException {
//         closeConnection();

//         String userKey = mapRole(role, "_USER");
//         String passKey = mapRole(role, "_PASS");

//         Connection conn = createConnection(userKey, passKey);
//         connectionThreadLocal.set(conn);
//     }

//     private Connection createConnection(String userKey, String passKey) throws SQLException {
//         String url = properties.getProperty("CONNECTION_URL");
//         String user = properties.getProperty(userKey);
//         String pass = properties.getProperty(passKey);

//         if (url == null || user == null || pass == null) {
//             throw new SQLException("Configurazione connessione non valida per " + userKey);
//         }

//         return DriverManager.getConnection(url, user, pass);
//     }

//     public void closeConnection() {
//         Connection conn = connectionThreadLocal.get();
//         if (conn != null) {
//             try {
//                 if (!conn.isClosed()) {
//                     if (!conn.getAutoCommit()) {
//                         conn.rollback();
//                     }
//                     conn.close();
//                 }
//             } catch (SQLException e) {
//                 LOGGER.log(Level.SEVERE, "Errore durante la chiusura della connessione", e);
//             } finally {
//                 connectionThreadLocal.remove();
//             }
//         }
//     }

//     private String mapRole(Role role, String suffix) {
//         return switch (role) {
//             case USER -> "user" + suffix;
//             case CUSTOMER -> "custumer" + suffix;
//         };
//     }

//     public void close() {
//         closeConnection();
//     }
// }
