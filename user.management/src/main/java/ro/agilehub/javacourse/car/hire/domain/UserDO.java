package ro.agilehub.javacourse.car.hire.domain;

import lombok.*;
import ro.agilehub.javacourse.car.hire.entity.Status;
import ro.agilehub.javacourse.car.hire.domain.CountryDO;


@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {

    private String id;
    private String email;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String title;
    private CountryDO countryOfResidence;
    private long driverLicenseNumber;
    private Status status;
}
