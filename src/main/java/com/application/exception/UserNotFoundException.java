package com.application.exception;

import java.time.LocalDateTime;

public class UserNotFoundException extends RuntimeException{
//    private LocalDateTime timestamp;
//    private int status;
//    private String errorMsg;
//    private String path;
    public UserNotFoundException(String msg){
        super(msg);
    }
}

