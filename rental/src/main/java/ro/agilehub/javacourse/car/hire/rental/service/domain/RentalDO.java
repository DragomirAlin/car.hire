package ro.agilehub.javacourse.car.hire.rental.service.domain;

import lombok.*;
import ro.agilehub.javacourse.car.hire.fleet.service.domain.CarDO;
import ro.agilehub.javacourse.car.hire.rental.entity.Status;
import ro.agilehub.javacourse.car.hire.user.service.domain.UserDO;

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

