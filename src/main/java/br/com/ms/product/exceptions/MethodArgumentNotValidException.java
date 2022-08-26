package br.com.ms.product.exceptions;

public class MethodArgumentNotValidException extends  RuntimeException {

    private static final long serialVerionUID = 1l;

    public MethodArgumentNotValidException(String message, Throwable cause){
        super(message, cause);
    }

    public MethodArgumentNotValidException(String message){
        super(message);
    }
}
