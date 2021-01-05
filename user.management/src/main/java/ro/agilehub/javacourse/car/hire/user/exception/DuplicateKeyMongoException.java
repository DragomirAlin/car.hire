package ro.agilehub.javacourse.car.hire.user.exception;

public class DuplicateKeyMongoException extends RuntimeException{
    public DuplicateKeyMongoException(String message){
        super(message);
    }
}
