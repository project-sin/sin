package sin.sin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sin.sin.dto.ErrorResponse;
import sin.sin.handler.exception.AlreadyExistedEmailException;
import sin.sin.handler.exception.AlreadyExistedIdException;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(AlreadyExistedEmailException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistedEmailException(
        AlreadyExistedEmailException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(AlreadyExistedIdException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistedIdException(
        AlreadyExistedIdException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(e.getMessage()));
    }
}
