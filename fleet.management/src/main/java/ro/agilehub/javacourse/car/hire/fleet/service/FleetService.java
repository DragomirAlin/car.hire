package ro.agilehub.javacourse.car.hire.fleet.service;

import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;

import javax.validation.Valid;
import java.util.List;

public interface FleetService {

    String addCar(CarDO carDO);
    void removeCar(String id);
    CarDO findById(String id);
    List<CarDO> findAll();
    CarDO updateCar(String id, @Valid JsonPatch jsonPatch);
    List<CarDO> findAllByStatus(String status);

}
