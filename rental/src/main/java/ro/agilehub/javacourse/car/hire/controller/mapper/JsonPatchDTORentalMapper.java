package ro.agilehub.javacourse.car.hire.controller.mapper;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatchDTO;
import ro.agilehub.javacourse.car.hire.service.model.JsonPatch;

@Mapper(componentModel = "spring")
public interface JsonPatchDTORentalMapper {

    JsonPatch toJsonPatch(JsonPatchDTO jsonPatchDTO);
}
