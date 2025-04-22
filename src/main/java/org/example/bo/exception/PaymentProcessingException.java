package org.example.bo.exception;

public class PaymentProcessingException extends Exception{
    public PaymentProcessingException(String error){
        super(error);
    }
}
