package ro.agilehub.javacourse.car.hire.fleet.domain;

import lombok.*;
import ro.agilehub.javacourse.car.hire.fleet.entity.Make;
import ro.agilehub.javacourse.car.hire.fleet.entity.Status;

@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDO {

    private String id;
    private Make make;
    private String model;
    private int year;
    private int mileage;
    private String fuel;
    private Status status;

}
