package ro.agilehub.javacourse.car.hire.fleet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import ro.agilehub.javacourse.car.hire.fleet.service.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.service.model.JsonPatch;

import java.util.List;

public interface CarService {

    String addCar(CarDO carDO);
    void removeCar(String id);
    CarDO findById(String id);
    List<CarDO> findAll();
    CarDO updateCar(String id, List<JsonPatch> jsonPatch) throws JsonPatchException, JsonProcessingException;
    List<CarDO> findAllByStatus(String status);

}
