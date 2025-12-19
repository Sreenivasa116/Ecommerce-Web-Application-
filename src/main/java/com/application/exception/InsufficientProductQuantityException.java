package com.application.exception;

public class InsufficientProductQuantityException extends RuntimeException{
    public InsufficientProductQuantityException(String msg){
        super(msg);
    }
}
