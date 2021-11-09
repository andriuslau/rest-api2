package com.juniorjavadeveloper.restapidemo.exception;

public class DataExistException extends RuntimeException{

    public DataExistException(String message) {
        super(message);
    }
}
