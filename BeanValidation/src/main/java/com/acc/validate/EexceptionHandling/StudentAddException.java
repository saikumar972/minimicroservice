package com.acc.validate.EexceptionHandling;

public class StudentAddException extends RuntimeException{
    String msg;
    public StudentAddException(String msg){
        super(msg);
    }
}
