package com.nr.chatapp.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserNameAlreadyExist.class)
    public ResponseEntity<?> userNameAlreadyExist(UserNameAlreadyExist userNameAlreadyExist) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage("UserName already exist");
        exceptionResponse.setLocalDateTime(LocalDateTime.now());
        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException userNotFoundException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage("Receiver details does not exists");
        exceptionResponse.setLocalDateTime(LocalDateTime.now());
        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String,List<String>> body = new HashMap<>();
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
