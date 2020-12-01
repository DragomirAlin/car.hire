package ro.agilehub.javacourse.domain;

import lombok.*;
import ro.agilehub.javacourse.car.hire.domain.UserDO;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.entity.Status;

@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalDO {

    private String id;

    private UserDO userDO;

    private CarDO carDO;

    private String startDate;

    private String endDate;

    private Status status;

}

