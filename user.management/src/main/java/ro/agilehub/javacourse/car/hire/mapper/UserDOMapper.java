package ro.agilehub.javacourse.car.hire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.domain.UserDO;
import ro.agilehub.javacourse.car.hire.entity.Country;
import ro.agilehub.javacourse.car.hire.entity.Status;
import ro.agilehub.javacourse.car.hire.entity.User;

@Mapper(componentModel = "spring", uses = {CountryDOMapper.class, ObjectIdMapper.class})
public interface UserDOMapper {

    @Mapping(target = "id", source = "user._id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "countryOfResidence", source = "country")
    @Mapping(target = "driverLicenseNumber", source = "user.driverLicenseNumber")
    @Mapping(target = "status", source = "status")
    UserDO toUserDO(User user, Country country, Status status);

    @Mapping(target = "_id", source = "userDO.id")
    @Mapping(target = "email", source = "userDO.email")
    @Mapping(target = "username", source = "userDO.username")
    @Mapping(target = "firstName", source = "userDO.firstName")
    @Mapping(target = "lastName", source = "userDO.lastName")
    @Mapping(target = "country", source = "countryDO.id")
    @Mapping(target = "status", source = "status")
    User toUser(UserDO userDO, CountryDO countryDO, Status status);


}
