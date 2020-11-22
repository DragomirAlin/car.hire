package ro.agilehub.javacourse.car.hire.fleet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.agilehub.javacourse.car.hire.fleet.entity.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDO {

    private String id;
    private String make;
    private String model;
    private int year;
    private int mileage;
    private String fuel;
    private Status status;

}
