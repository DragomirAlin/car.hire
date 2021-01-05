package ro.agilehub.javacourse.car.hire.user.service;

import ro.agilehub.javacourse.car.hire.user.service.domain.CountryDO;

public interface CountryService {

    CountryDO findByIsoCode(String isoCode);

}
