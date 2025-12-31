package com.sgt.expense_tracker.exceptions;

public class UsernameAlreadyExistsException extends Exception{
    public UsernameAlreadyExistsException(){
        super("Username entered already exists!!");
    }

}
