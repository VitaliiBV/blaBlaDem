package com.example.blabladem.exception;

public class TaskNotFoundException extends BadRequestException{

    public TaskNotFoundException(String message) {
        super(message);
    }
}
