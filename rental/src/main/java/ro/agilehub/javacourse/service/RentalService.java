package ro.agilehub.javacourse.service;

import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.RentDTO;
import ro.agilehub.javacourse.domain.RentalDO;
import ro.agilehub.javacourse.entity.Rental;

import java.util.List;

public interface RentalService {

    int addRent(RentalDO rentalDO);
    void removeRent(Integer id);
    RentalDO getRent(Integer id);
    List<RentalDO> getRents();
    RentalDO updateRent(Integer id, List<PatchDocument> patchDocument);
}
