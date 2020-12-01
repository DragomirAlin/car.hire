package ro.agilehub.javacourse.car.hire.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String id){
        super(String.format("Object is not found with ID : '%s'", id));
    }
}
