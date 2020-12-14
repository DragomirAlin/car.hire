package ro.agilehub.javacourse.car.hire.fleet.mapper;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatchDTO;
import ro.agilehub.javacourse.car.hire.fleet.model.JsonPatch;

@Mapper(componentModel = "spring")
public interface JsonPatchFleetDTOMapper {

    JsonPatch toJsonPatch(JsonPatchDTO jsonPatchDTO);
}
