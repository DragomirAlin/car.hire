package ro.agilehub.javacourse.car.hire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserResponseDTO;
import ro.agilehub.javacourse.car.hire.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.domain.UserDO;
import ro.agilehub.javacourse.car.hire.entity.Country;
import ro.agilehub.javacourse.car.hire.entity.Status;

@Mapper(componentModel = "spring", uses = CountryDOMapper.class)
public interface UserDTOMapper {


    UserResponseDTO toUserResponseDTO(UserDO userDO);

    @Mapping(target = "id", source = "userDTO.id")
    @Mapping(target = "email", source = "userDTO.email")
    @Mapping(target = "username", source = "userDTO.username")
    @Mapping(target = "firstName", source = "userDTO.firstname")
    @Mapping(target = "lastName", source = "userDTO.lastname")
    @Mapping(target = "countryOfResidence", source = "countryDO")
    @Mapping(target = "status", source = "status")
    UserDO toUserDO(UserDTO userDTO, CountryDO countryDO, Status status);

}
