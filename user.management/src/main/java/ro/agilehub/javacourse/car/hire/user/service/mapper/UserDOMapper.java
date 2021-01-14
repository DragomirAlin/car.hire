package ro.agilehub.javacourse.car.hire.user.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.entity.Status;
import ro.agilehub.javacourse.car.hire.user.entity.User;
import ro.agilehub.javacourse.car.hire.user.service.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.user.service.domain.UserDO;

@Mapper(componentModel = "spring", uses = {CountryDOMapper.class, ObjectIdMapper.class})
public interface UserDOMapper {

    @Mapping(target = "id", source = "user._id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "firstname", source = "user.firstname")
    @Mapping(target = "lastname", source = "user.lastname")
    @Mapping(target = "countryDO", source = "country")
    @Mapping(target = "driverLicense", source = "user.driverLicense")
    @Mapping(target = "status", source = "user.status")
    @Mapping(target = "title", source = "user.title")
    UserDO toUserDO(User user, Country country);

    @Mapping(target = "_id", source = "userDO.id")
    @Mapping(target = "email", source = "userDO.email")
    @Mapping(target = "username", source = "userDO.username")
    @Mapping(target = "firstname", source = "userDO.firstname")
    @Mapping(target = "lastname", source = "userDO.lastname")
    @Mapping(target = "countryId", source = "countryDO.id")
    @Mapping(target = "driverLicense", source = "userDO.driverLicense")
    @Mapping(target = "status", source = "userDO.status")
    @Mapping(target = "title", source = "userDO.title")
    User toUser(UserDO userDO, CountryDO countryDO);

}