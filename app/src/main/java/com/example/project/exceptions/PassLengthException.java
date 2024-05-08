package com.example.project.exceptions;

public class PassLengthException extends RuntimeException{
    public PassLengthException(String message)
    {
        super(message);
    }

    public PassLengthException()
    {
        super();
    }
}
