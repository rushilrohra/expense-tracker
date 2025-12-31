package com.sgt.expense_tracker.exceptions;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(){
        super("Entered Email is invalid");
    }
}
