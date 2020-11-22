package ro.agilehub.javacourse.car.hire.fleet.service;

import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;

import java.util.List;

@Service
public class FleetServiceImpl implements FleetService {


    @Override
    public int addCar(CarDTO carDTO) {
        return 0;
    }

    @Override
    public void removeCar(Integer id) {

    }

    @Override
    public CarDO getCar(Integer id) {
        return null;
    }

    @Override
    public List<CarDO> getCars() {
        return null;
    }

    @Override
    public CarDO updateCar(Integer id, List<PatchDocument> patchDocument) {
        return null;
    }

    @Override
    public List<CarDO> getCarsByStatus(String status) {
        return null;
    }
}
