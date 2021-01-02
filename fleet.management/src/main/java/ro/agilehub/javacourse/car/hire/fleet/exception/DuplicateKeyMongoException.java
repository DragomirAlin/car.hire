package ro.agilehub.javacourse.car.hire.fleet.exception;

public class DuplicateKeyMongoException extends RuntimeException{
    public DuplicateKeyMongoException(String message){
        super(message);
    }
}
