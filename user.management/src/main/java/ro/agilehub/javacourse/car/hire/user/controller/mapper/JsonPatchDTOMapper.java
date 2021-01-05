package ro.agilehub.javacourse.car.hire.user.controller.mapper;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatchDTO;
import ro.agilehub.javacourse.car.hire.user.service.model.JsonPatch;

@Mapper(componentModel = "spring")
public interface JsonPatchDTOMapper {

    JsonPatch toJsonPatch(JsonPatchDTO jsonPatchDTO);
}
