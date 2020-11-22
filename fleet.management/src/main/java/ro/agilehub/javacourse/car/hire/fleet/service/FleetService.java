package ro.agilehub.javacourse.car.hire.fleet.service;

import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;

import java.util.List;

public interface FleetService {

    int addCar(CarDTO carDO);
    void removeCar(Integer id);
    CarDO getCar(Integer id);
    List<CarDO> getCars();
    CarDO updateCar(Integer id, List<PatchDocument> patchDocument);
    List<CarDO> getCarsByStatus(String status);

}
