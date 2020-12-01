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
    private String firstname;
    private String lastname;
    private String title;
    private CountryDO countryDO;
    private long driverlicensenumber;
    private Status status;
}
