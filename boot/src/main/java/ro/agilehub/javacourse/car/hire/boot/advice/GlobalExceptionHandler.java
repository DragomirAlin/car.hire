package ro.agilehub.javacourse.car.hire.boot.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.agilehub.javacourse.car.hire.user.exception.DuplicateFieldException;
import ro.agilehub.javacourse.car.hire.user.exception.UserManagementValidationException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public void handleNotFound() {
        // Nothing to do
    }

    @ExceptionHandler(UserManagementValidationException.class)
    public ResponseEntity<DuplicateFieldException> handleValidationException(UserManagementValidationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getDuplicateFieldException());
    }
}
