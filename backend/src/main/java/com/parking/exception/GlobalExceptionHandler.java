package com.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SlotNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(SlotNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(LocalDateTime.now(), 404, "Not Found", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({SlotAlreadyFreeException.class, SlotAlreadyExistsException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(LocalDateTime.now(), 400, "Bad Request", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    // ✅ catch any remaining RuntimeException too (important)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(LocalDateTime.now(), 400, "Bad Request", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    // ✅ TEMP DEBUG: show exact error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        String msg = ex.getClass().getSimpleName() + ": " + ex.getMessage();

        return new ResponseEntity<>(
                new ErrorResponse(LocalDateTime.now(), 500, "Internal Server Error", msg),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
