package com.example.project.exceptions;

public class WhiteSpaceException extends RuntimeException{

    public WhiteSpaceException(String message)
    {
        super(message);
    }

    public WhiteSpaceException()
    {
        super();
    }
}
