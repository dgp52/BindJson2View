package com.dgp52.bindjson2viewlib.logexception.exceptions;

public class InvalidUnitException extends Exception{
    public InvalidUnitException() {super("Unit not found");}
    public InvalidUnitException(String message){super(message);}
}
