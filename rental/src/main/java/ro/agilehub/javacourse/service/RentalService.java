package ro.agilehub.javacourse.service;

import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.RentDTO;

import java.util.List;

public interface RentalService {

    int addRent(RentDO rentDO);
    void removeRent(Integer id);
    RentDO getRent(Integer id);
    List<RentDO> getRents();
    RentDO updateRent(Integer id, List<PatchDocument> patchDocument);
}
