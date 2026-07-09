package com.michaelcyrus.bankapp.exception;

import com.michaelcyrus.bankapp.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmailException(
            DuplicateEmailException exception
    ) {
        return buildErrorResponse(
                HttpStatus.CONFLICT,
                "Conflict",
                exception.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception
    ) {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Validation failed");

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Bad Request",
                message
        );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
            InvalidCredentialsException exception
    ) {
        return buildErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Unauthorized",
                exception.getMessage()
        );
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientFundsException(
            InsufficientFundsException exception
    ) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Bad Request",
                exception.getMessage()
        );
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus status,
            String error,
            String message
    ) {
        ErrorResponse response = new ErrorResponse(
                OffsetDateTime.now().toString(),
                status.value(),
                error,
                message
        );

        return ResponseEntity
                .status(status)
                .body(response);
    }
}