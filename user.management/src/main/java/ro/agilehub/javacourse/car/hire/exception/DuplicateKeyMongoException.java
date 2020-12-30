package ro.agilehub.javacourse.car.hire.exception;

public class DuplicateKeyMongoException extends RuntimeException{
    public DuplicateKeyMongoException(String message){
        super(message);
    }
}
