package ro.agilehub.javacourse.car.hire.exception;

import lombok.Getter;

@Getter
public class UserManagementValidationException extends RuntimeException {

    private final DuplicateFieldException duplicateFieldException;

    public UserManagementValidationException(DuplicateFieldException duplicateFieldException, DuplicateFieldException duplicateFieldException1) {
        super();
        this.duplicateFieldException = duplicateFieldException1;
    }
}
