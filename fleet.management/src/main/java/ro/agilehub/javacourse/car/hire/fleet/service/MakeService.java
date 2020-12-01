package ro.agilehub.javacourse.car.hire.fleet.service;

import ro.agilehub.javacourse.car.hire.fleet.domain.MakeDO;

public interface MakeService {

    MakeDO findByMakeName(String makeName);
}
