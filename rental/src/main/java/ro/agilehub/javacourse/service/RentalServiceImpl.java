package ro.agilehub.javacourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.domain.RentalDO;
import ro.agilehub.javacourse.repository.RentalRepository;

import java.util.List;

public class RentalServiceImpl implements RentalService{

    @Autowired
    private RentalRepository rentalRepository;


    @Override
    public int addRent(RentalDO rentalDO) {
        return 0;
    }

    @Override
    public void removeRent(Integer id) {

    }

    @Override
    public RentalDO getRent(Integer id) {
        return null;
    }

    @Override
    public List<RentalDO> getRents() {
        return null;
    }

    @Override
    public RentalDO updateRent(Integer id, List<PatchDocument> patchDocument) {
        return null;
    }
}
