package com.example.project.exceptions;

public class NoInputException extends RuntimeException{

    public NoInputException(String message)
    {
        super(message);
    }

    public NoInputException()
    {
        super();
    }
}
