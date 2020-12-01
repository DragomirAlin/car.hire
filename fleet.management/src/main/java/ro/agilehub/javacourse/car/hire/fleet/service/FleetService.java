package ro.agilehub.javacourse.car.hire.fleet.service;

import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;

import java.util.List;

public interface FleetService {

    String addCar(CarDTO carDTO);
    void removeCar(String id);
    CarDO findById(String id);
    List<CarDO> findAll();
    CarDO updateCar(String id, List<PatchDocument> patchDocument);
    List<CarDO> findAllByStatus(String status);

}
