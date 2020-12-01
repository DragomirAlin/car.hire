package ro.agilehub.javacourse.car.hire.service;

import ro.agilehub.javacourse.car.hire.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.entity.Country;

public interface CountryService {

    CountryDO findByIsoCode(String isoCode);

}
