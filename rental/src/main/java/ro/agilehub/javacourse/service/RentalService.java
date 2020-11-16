package ro.agilehub.javacourse.service;

import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.api.model.RentDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;

import java.util.List;

public interface RentalService {

    int addRent(RentDTO rentDTO);
    void removeRent(Integer id);
    RentDTO getRent(Integer id);
    List<RentDTO> getRents();
    RentDTO updateRent(Integer id, JsonPatch jsonPatch);
}
