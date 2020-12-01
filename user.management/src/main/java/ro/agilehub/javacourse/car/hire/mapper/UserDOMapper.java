package ro.agilehub.javacourse.car.hire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.domain.UserDO;
import ro.agilehub.javacourse.car.hire.entity.Country;
import ro.agilehub.javacourse.car.hire.entity.User;

@Mapper(componentModel = "spring", uses = {CountryDOMapper.class, ObjectIdMapper.class})
public interface UserDOMapper {

    @Mapping(target = "id", source = "user._id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "firstname", source = "user.firstname")
    @Mapping(target = "lastname", source = "user.lastname")
    @Mapping(target = "title", source = "user.title")
    @Mapping(target = "countryDO", source = "country")
    @Mapping(target = "driverlicensenumber", source = "user.driverlicensenumber")
    @Mapping(target = "status", source = "user.status")
    UserDO toUserDO(User user, Country country);

    @Mapping(target = "_id", source = "userDO.id")
    @Mapping(target = "email", source = "userDO.email")
    @Mapping(target = "username", source = "userDO.username")
    @Mapping(target = "firstname", source = "userDO.firstname")
    @Mapping(target = "lastname", source = "userDO.lastname")
    @Mapping(target = "title", source = "userDO.title")
    @Mapping(target = "country_id", source = "userDO.countryDO.id")
    @Mapping(target = "driverlicensenumber", source = "userDO.driverlicensenumber")
    @Mapping(target = "status", source = "userDO.status")
    User toUser(UserDO userDO);


}
