package br.ueg.progweb1.empresa.exceptions;

public class DataException extends RuntimeException {
    public DataException(String message, Throwable e) {
        super(message);
    }

    public DataException(String message) {
        super(message);
    }
}
