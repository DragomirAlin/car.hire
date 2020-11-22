package ro.agilehub.javacourse.car.hire.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDO {

    private String id;

    private String name;

    private String isoCode;
}
