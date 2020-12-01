package ro.agilehub.javacourse.car.hire.service;

import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.RentalDTO;
import ro.agilehub.javacourse.car.hire.domain.RentalDO;

import javax.validation.Valid;
import java.util.List;

public interface RentalService {

    String addRent(RentalDTO rentalDTO);
    void removeRent(String id);
    RentalDO findById(String id);
    List<RentalDO> findAll();
    RentalDO updateRent(String id, @Valid JsonPatch jsonPatch);
}
