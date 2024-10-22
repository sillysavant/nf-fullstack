package com.training.nf.exception;

import com.training.nf.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Handles exception
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({RepeatDataException.class})
    protected ResponseEntity<ErrorResponse> handleRepeatDataException(RuntimeException exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.OK.toString(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(RuntimeException exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoContentException.class})
    protected ResponseEntity<ErrorResponse> handleNoContentException(RuntimeException exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NO_CONTENT.toString(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "The argument is invalid!", errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        String name = exception.getName();
        String type = Objects.requireNonNull(exception.getRequiredType()).getSimpleName();
        Object value = exception.getValue();
        String message = String.format("The '%s' should be a valid '%s' and '%s' isn't!", name, type, value);
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), message);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
