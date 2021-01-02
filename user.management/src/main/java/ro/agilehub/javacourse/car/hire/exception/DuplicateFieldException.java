package ro.agilehub.javacourse.car.hire.exception;

public class DuplicateFieldException extends RuntimeException {

    private final String collectionName;
    private final String input;
    private final String fieldName;

    public DuplicateFieldException(String fieldName, String input, String collectionName) {
        super(String.format("%s = %s field already exists in %s collection", fieldName, input, collectionName));
        this.collectionName = collectionName;
        this.input = input;
        this.fieldName = fieldName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getInput() {
        return input;
    }

    public String getFieldName() {
        return fieldName;
    }


}

