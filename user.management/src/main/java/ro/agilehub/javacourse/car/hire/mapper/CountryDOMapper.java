package ro.agilehub.javacourse.car.hire.mapper;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.entity.Country;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface CountryDOMapper {

    CountryDO toCountryDO(Country country);
    Country toCountry(CountryDO countryDO);
}
