package ro.agilehub.javacourse.car.hire.fleet.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.mapper.CarDOMapper;
import ro.agilehub.javacourse.car.hire.fleet.mapper.CarDTOMapper;
import ro.agilehub.javacourse.car.hire.fleet.repository.FleetRepository;
import ro.agilehub.javacourse.car.hire.fleet.repository.MakeRepository;
import ro.agilehub.javacourse.car.hire.fleet.service.FleetService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class FleetServiceImpl implements FleetService {

    @Autowired
    private FleetRepository fleetRepository;

    @Autowired
    private MakeRepository makeRepository;

    @Autowired
    private CarDOMapper mapper;

    @Autowired
    private CarDTOMapper mapperDTO;

    @Override
    public String addCar(CarDO carDO) {
        var car = mapper.toCar(carDO);
        return fleetRepository.save(car).get_id().toString();
    }

    @Override
    public void removeCar(String id) {
        var car = fleetRepository
                .findById(new ObjectId(id))
                .orElseThrow();

        fleetRepository.delete(car);
    }

    @Override
    public CarDO findById(String id) {
        return fleetRepository
                .findById(new ObjectId(id))
                .map(this::map)
                .orElseThrow();
    }

    @Override
    public List<CarDO> findAll() {
        return fleetRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(toList());
    }

    @Override
    public CarDO updateCar(String id, @Valid JsonPatch jsonPatch) {
        return null;
    }

    @Override
    public List<CarDO> findAllByStatus(String status) {
        return fleetRepository
                .findAllByStatus(status)
                .stream()
                .map(this::map)
                .collect(toList());
    }

    private CarDO map(Car car) {
        var make = makeRepository
                .findById(new ObjectId(car.getMake_id()))
                .orElse(null);

        return mapper.toCarDO(car, make);
    }

}
