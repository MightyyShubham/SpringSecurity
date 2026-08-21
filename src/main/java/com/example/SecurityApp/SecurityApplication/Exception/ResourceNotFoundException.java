package com.example.SecurityApp.SecurityApplication.Exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
