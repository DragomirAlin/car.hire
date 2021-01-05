package ro.agilehub.javacourse.car.hire.user.exception;

public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 6769829255364211880L;

    public NotFoundException(String id){
        super(String.format("Object is not found with ID : '%s'", id));
    }
}
