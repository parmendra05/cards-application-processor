package com.banking.cards.application.handler;

import com.banking.cards.application.handler.exception.ApplicationAlreadyProcessedException;
import com.banking.cards.application.handler.exception.ResourceNotFoundException;
import com.banking.cards.application.model.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class CardsApplicationExceptionHandler {

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentException(MethodArgumentNotValidException ex) {
        ApiResponse<?> response = ApiResponse.builder().errors(Map.of("developer message", ex.getMessage())).build();
    }*/

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(getDeveloperMessage(ex));
    }

    @ExceptionHandler(ApplicationAlreadyProcessedException.class)
    public ResponseEntity<ApiResponse<?>> handleApplicationAlreadyProcessedException(ApplicationAlreadyProcessedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(getDeveloperMessage(ex));
    }

    private static ApiResponse<?> getDeveloperMessage(RuntimeException ex) {
        return ApiResponse.builder().errors(Map.of("developer message", ex.getMessage())).build();
    }

}
