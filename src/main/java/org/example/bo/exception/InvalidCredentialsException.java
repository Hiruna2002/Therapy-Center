package org.example.bo.exception;

public class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException(String error){
        super(error);
    }
}
