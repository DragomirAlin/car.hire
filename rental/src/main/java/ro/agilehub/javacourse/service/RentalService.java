package ro.agilehub.javacourse.service;

import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.RentDTO;

import java.util.List;

public interface RentalService {

    int addRent(RentDTO rentDTO);
    void removeRent(Integer id);
    RentDTO getRent(Integer id);
    List<RentDTO> getRents();
    RentDTO updateRent(Integer id, List<PatchDocument> patchDocument);
}
