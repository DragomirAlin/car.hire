package ro.agilehub.javacourse.car.hire.exception;

public class DuplicateFieldException extends RuntimeException {

    public DuplicateFieldException(String collectionName, String input, String fieldName) {
        super(String.format("%s = %s field already exists in %s collection", collectionName, input, fieldName));
    }

}

