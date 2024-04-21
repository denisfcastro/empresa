package br.ueg.progweb1.empresa.exceptions;

public class BusinessLogicException extends RuntimeException {
    private BusinessLogicError error;

    public BusinessLogicException(String message, Throwable e) {
        super(message);
        this.error = BusinessLogicError.GERAL;

    }

    public BusinessLogicException(String message) {
        super(message);
        this.error = BusinessLogicError.GERAL;

    }

    public BusinessLogicException(BusinessLogicError error){
        super(error.getMessage());
        this.error = error;
    }

}
