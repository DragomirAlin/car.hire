package ro.agilehub.javacourse.car.hire.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserResponseDTO;
import ro.agilehub.javacourse.car.hire.service.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.service.domain.UserDO;

@Mapper(componentModel = "spring", uses = {CountryDOMapper.class, ObjectIdMapper.class})
public interface UserDTOMapper {


    @Mapping(target = "id", source = "userDO.id")
    @Mapping(target = "email", source = "userDO.email")
    @Mapping(target = "username", source = "userDO.username")
    @Mapping(target = "firstname", source = "userDO.firstname")
    @Mapping(target = "lastname", source = "userDO.lastname")
    @Mapping(target = "title", source = "userDO.title")
    @Mapping(target = "countryofresidence", source = "userDO.countryDO.isoCode")
    @Mapping(target = "status", source = "userDO.status")
    UserResponseDTO toUserResponseDTO(UserDO userDO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "userDTO.email")
    @Mapping(target = "username", source = "userDTO.username")
    @Mapping(target = "firstname", source = "userDTO.firstname")
    @Mapping(target = "lastname", source = "userDTO.lastname")
    @Mapping(target = "title", source = "userDTO.title")
    @Mapping(target = "countryDO", source = "countryDO")
    @Mapping(target = "driverlicensenumber", source = "userDTO.driverlicensenumber")
    @Mapping(target = "status", source = "userDTO.status")
    UserDO toUserDO(UserDTO userDTO, CountryDO countryDO);

}