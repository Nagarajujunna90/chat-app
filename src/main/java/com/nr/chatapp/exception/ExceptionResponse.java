package com.nr.chatapp.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ExceptionResponse {
    private String message;
    private LocalDateTime localDateTime;
    private int statusCode;


}
