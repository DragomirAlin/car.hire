package ro.agilehub.javacourse.car.hire.user.service;

import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.service.domain.CountryDO;

public interface CountryService {

    CountryDO findByIsoCode(String isoCode);
    CountryDO saveCountry(Country country);

}
