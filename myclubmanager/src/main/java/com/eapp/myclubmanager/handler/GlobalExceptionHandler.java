package com.eapp.myclubmanager.handler;

import com.eapp.myclubmanager.exception.OperationNotPermittedException;
import com.eapp.myclubmanager.exception.SwimmerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exception){

        Set<String> errors = new HashSet<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(error.getDefaultMessage());
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ExceptionResponse.Builder()
                                .setValidationErrors(errors)
                                .createExceptionResponse()
                );
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleException(OperationNotPermittedException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ExceptionResponse.Builder()
                                .setBusinessErrorDescription(exception.getMessage())
                                .setError(exception.getMessage())
                                .createExceptionResponse()
                );
    }

    @ExceptionHandler(SwimmerNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(SwimmerNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ExceptionResponse.Builder()
                                .setBusinessErrorDescription(exception.getMessage())
                                .setError(exception.getMessage())
                                .createExceptionResponse()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception){
        exception.printStackTrace(); // TODO replace with logger

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ExceptionResponse.Builder()
                                .setBusinessErrorDescription("Internal error, contact the admin")
                                .setError(exception.getMessage())
                                .createExceptionResponse()
                );
    }
}
