package ro.agilehub.javacourse.car.hire.fleet.service;

import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.FleetDTO;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;

import java.util.List;

public interface FleetService {

    int addCar(CarDTO carDTO);
    void removeCar(Integer id);
    CarDTO getCar(Integer id);
    List<CarDTO> getCars();
    CarDTO updateCar(Integer id, List<PatchDocument> patchDocument);
    List<CarDTO> getCarsByStatus(String status);

}
