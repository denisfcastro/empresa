package br.ueg.progweb1.empresa.exceptions;

public class BusinessLogicException extends RuntimeException {
    public BusinessLogicException(String message, Throwable e) {
        super(message);
    }

    public BusinessLogicException(String message) {
        super(message);
    }
}
