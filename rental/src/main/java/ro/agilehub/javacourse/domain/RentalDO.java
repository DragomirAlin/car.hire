package ro.agilehub.javacourse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.agilehub.javacourse.car.hire.domain.UserDO;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.entity.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDO {

    private String id;

    private UserDO user_id;

    private CarDO car_id;

    private String startDate;

    private String endDate;

    private Status status;

}

