package ro.agilehub.javacourse.car.hire.domain;

import lombok.*;
import ro.agilehub.javacourse.car.hire.entity.Country;
import ro.agilehub.javacourse.car.hire.entity.Status;

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
    private Country countryOfResidence;
    private long driverLicenseNumber;
    private Status status;
}
