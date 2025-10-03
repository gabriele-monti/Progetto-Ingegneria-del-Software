package it.uniroma2.dicii.foodmood.model;

public record LoginResult(
    Credentials credenziali,
    int errorCode,
    String errorMessage
) {
    public boolean isSuccessful() {
        return errorCode == 0;
    }
    
    public String mostraMessaggioErrore() {
        return switch(errorCode) {
            case 0 -> "Login riuscito\n";
            case 1 -> "Username non trovato\n";
            case 2 -> "Password errata\n";
            case 3 -> "Ruolo non valido\n";
            default -> "Errore di autenticazione: " + errorMessage + "\n";
        };
    }
}