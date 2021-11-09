package com.juniorjavadeveloper.restapidemo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorDetails {

    private HttpStatus status;
    private String message;
    private LocalDateTime timeStamp;

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message == null ? status.getReasonPhrase() : message;
    }
}
