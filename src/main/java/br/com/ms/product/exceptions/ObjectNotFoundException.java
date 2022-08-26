package br.com.ms.product.exceptions;

public class ObjectNotFoundException extends  RuntimeException {

    private static final long serialVerionUID = 1l;

    public ObjectNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ObjectNotFoundException(String message){
        super(message);
    }
}
