package com.devsenior.cdiaz.userlogin.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsenior.cdiaz.userlogin.exception.BadLoginException;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiErrorResponse> handleExpiredJwtException(ExpiredJwtException ex,
            HttpServletRequest request) {
        var response = new ApiErrorResponse(
                HttpStatus.FORBIDDEN,
                "Ha expirado el token. Genera uno nuevo",
                request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadLoginException.class)
    public ResponseEntity<ApiErrorResponse> handleBadLoginException(BadLoginException ex, HttpServletRequest request) {
        var response = new ApiErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Credenciales incorrectas",
                request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex,
            HttpServletRequest request) {
        var response = new ApiErrorResponse(
                HttpStatus.UNAUTHORIZED,
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<ApiErrorResponse> handleAuthenticationException(AuthenticationException ex,
            HttpServletRequest request) {
        var response = new ApiErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Credenciales incorrectas",
                request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @Data
    public class ApiErrorResponse {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime timestamp;

        private Integer status;
        private String error;
        private String message;
        private String path;

        public ApiErrorResponse(HttpStatus status, String message, String path) {
            this.timestamp = LocalDateTime.now();
            this.status = status.value();
            this.error = status.getReasonPhrase();
            this.message = message;
            this.path = path;
        }

    }

}
