package com.acc.validate.EexceptionHandling;

public class IdInValidException extends RuntimeException{
    String msg;
    public IdInValidException(String msg){
        super(msg);
    }
}
