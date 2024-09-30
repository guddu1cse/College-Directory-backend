package com.CollegeDirectoryApplication.CollegeDirectoryApplication.ExceptionHandler;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String message){
        super(message);
    }
}
