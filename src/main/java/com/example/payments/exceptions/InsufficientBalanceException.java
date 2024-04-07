package com.example.payments.exceptions;

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(){
        super("Insufficient Balance");
    }
    public InsufficientBalanceException(String message){
        super(message);
    }
    public InsufficientBalanceException(String message,Throwable cause)
    {
        super(message,cause);
    }}
