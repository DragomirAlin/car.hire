package ro.agilehub.javacourse.car.hire.user.service.domain;

import lombok.*;

@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDO {

    private String id;

    private String name;

    private String isoCode;
}
