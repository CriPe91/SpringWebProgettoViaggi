package com.example.SpringWebProgetto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ApiError extends RuntimeException {
    public ApiError(String message) {
        super(message);
    }
    private String message;
    private HttpStatus httpStatus;
}
