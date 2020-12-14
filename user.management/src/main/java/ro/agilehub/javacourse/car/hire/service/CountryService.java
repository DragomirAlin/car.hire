package ro.agilehub.javacourse.car.hire.service;

import ro.agilehub.javacourse.car.hire.service.domain.CountryDO;

public interface CountryService {

    CountryDO findByIsoCode(String isoCode);

}
