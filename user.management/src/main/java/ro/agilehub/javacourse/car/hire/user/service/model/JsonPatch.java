package ro.agilehub.javacourse.car.hire.user.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JsonPatch {

    @JsonProperty("op")
    private String op;

    @JsonProperty("path")
    private String path;

    @JsonProperty("value")
    private Object value;

    @JsonProperty("from")
    private String from;
}
