package com.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private int status;
    private String errorMsg;
    private String path;
    private Map<String,String> errorDetails;

}
