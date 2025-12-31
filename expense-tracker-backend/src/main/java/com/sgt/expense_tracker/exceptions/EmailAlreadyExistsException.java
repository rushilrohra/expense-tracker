package com.sgt.expense_tracker.exceptions;

public class EmailAlreadyExistsException extends Exception{
    public EmailAlreadyExistsException(){
        super("Email entered already exists!");
    }
}
