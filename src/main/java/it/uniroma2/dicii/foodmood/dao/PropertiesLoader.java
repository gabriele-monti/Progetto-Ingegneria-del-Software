// package it.uniroma2.dicii.foodmood.dao;

// import java.io.*;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.Properties;

// public class PropertiesLoader {
//     private static final String[] PROPERTIES_PATHS = {
//         "db.properties",                     
//         "src/main/resources/db.properties",      
//         "config/db.properties"                   
//     };
    
//     private static Properties properties;

//     private PropertiesLoader() {
//     }

//     public static synchronized Properties getProperties() {
//         if (properties == null) {
//             properties = new Properties();
//             boolean loaded = false;
            
//             for (String path : PROPERTIES_PATHS) {
//                 if (tryLoadProperties(path)) {
//                     loaded = true;
//                     break;
//                 }
//             }
            
//             if (!loaded) {
//                 throw new RuntimeException("Impossibile trovare o caricare il file di configurazione");
//             }
            
//             validateRequiredProperties();
//         }
//         return properties;
//     }
    
//     private static boolean tryLoadProperties(String path) {
//         try {
//             InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(path);
            
//             if (input == null) {
//                 Path filePath = Paths.get(path);
//                 if (Files.exists(filePath)) {
//                     input = Files.newInputStream(filePath);
//                 } else {
//                     return false;
//                 }
//             }
            
//             try (InputStream closeable = input) {
//                 properties.load(closeable);
//                 return true;
//             }
//         } catch (IOException e) {
//             return false;
//         }
//     }
    
//     private static void validateRequiredProperties() {
//         String[] requiredProps = {
//             "CONNECTION_URL", 
//             "LOGIN_USER", "LOGIN_PASS",
//             "ADMINISTRATOR_USER", "ADMINISTRATOR_PASS",
//             "COMMERCIALE_USER", "COMMERCIALE_PASS",
//             "LOGISTICO_USER", "LOGISTICO_PASS"
//         };
        
//         for (String prop : requiredProps) {
//             if (!properties.containsKey(prop) || properties.getProperty(prop).isEmpty()) {
//                 throw new RuntimeException("Proprietà obbligatoria mancante: " + prop);
//             }
//         }
//     }

// }