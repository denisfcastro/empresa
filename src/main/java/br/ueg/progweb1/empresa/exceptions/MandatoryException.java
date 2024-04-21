package br.ueg.progweb1.empresa.exceptions;

import lombok.Getter;

public class MandatoryException extends RuntimeException {

    public MandatoryException(String message, Throwable e) {
        super(message, e);
    }

    public MandatoryException(String message) {
        super(message);

    }


}
