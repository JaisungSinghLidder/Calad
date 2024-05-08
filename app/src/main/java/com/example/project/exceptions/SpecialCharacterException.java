package com.example.project.exceptions;

public class SpecialCharacterException extends RuntimeException{

    public SpecialCharacterException(String message)
    {
        super(message);
    }

    public SpecialCharacterException()
    {
        super();
    }
}

