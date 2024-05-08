package com.example.project.exceptions;

public class EmailFormatException extends RuntimeException{

    public EmailFormatException(String message)
    {
        super(message);
    }

    public EmailFormatException()
    {
        super();
    }


}
