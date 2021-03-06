package ro.agilehub.javacourse.car.hire.fleet.service.domain;

import lombok.*;

@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakeDO {

    private String id;
    private String makeName;
}
