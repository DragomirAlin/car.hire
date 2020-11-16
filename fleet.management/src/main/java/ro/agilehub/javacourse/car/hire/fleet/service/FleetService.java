package ro.agilehub.javacourse.car.hire.fleet.service;

import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.FleetDTO;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;

import java.util.List;

public interface FleetService {

    int addCar(CarDTO carDTO);
    void removeCar(Integer id);
    CarDTO getCar(Integer id);
    List<CarDTO> getCars();
    CarDTO updateCar(Integer id, JsonPatch jsonPatch);

}
