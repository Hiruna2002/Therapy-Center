package org.example.bo.exception;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String error){
        super(error);
    }

}
