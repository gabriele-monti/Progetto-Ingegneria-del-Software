package it.uniroma2.dicii.foodmood.exceptions;

public class DAOException extends Exception {
    
    private final int errorCode;
    
    public DAOException(String message) {
        super(message);
        this.errorCode = 0;
    }
    
    public DAOException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = 0;
    }
    
    public DAOException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public DAOException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        if (errorCode != 0) {
            return getMessage();
        }
        return super.toString();
    }
}