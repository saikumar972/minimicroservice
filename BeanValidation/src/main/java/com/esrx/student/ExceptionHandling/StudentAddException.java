package com.esrx.student.ExceptionHandling;

public class StudentAddException extends RuntimeException{
    String msg;
    public StudentAddException(String msg){
        super(msg);
    }
}
