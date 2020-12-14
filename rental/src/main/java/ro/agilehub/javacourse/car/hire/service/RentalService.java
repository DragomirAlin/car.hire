package ro.agilehub.javacourse.car.hire.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import ro.agilehub.javacourse.car.hire.service.domain.RentalDO;
import ro.agilehub.javacourse.car.hire.service.model.JsonPatch;

import java.util.List;

public interface RentalService {

    String addRent(RentalDO rentalDO);
    void removeRent(String id);
    RentalDO findById(String id);
    List<RentalDO> findAll();
    RentalDO updateRent(String id, List<JsonPatch> jsonPatch) throws JsonPatchException, JsonProcessingException;
}
