package com.esrx.student.ExceptionHandling;

public class IdInValidException extends RuntimeException{
    String msg;
    public IdInValidException(String msg){
        super(msg);
    }
}
