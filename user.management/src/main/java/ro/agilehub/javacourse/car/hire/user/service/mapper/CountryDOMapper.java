package ro.agilehub.javacourse.car.hire.user.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.user.service.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.user.entity.Country;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface CountryDOMapper {

    @Mapping(target = "id", source = "country._id")
    CountryDO toCountryDO(Country country);
    Country toCountry(CountryDO countryDO);
}
