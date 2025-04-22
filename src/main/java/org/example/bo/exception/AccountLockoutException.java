package org.example.bo.exception;

public class AccountLockoutException extends Exception{
    public AccountLockoutException(String error){
        super(error);
    }
}
