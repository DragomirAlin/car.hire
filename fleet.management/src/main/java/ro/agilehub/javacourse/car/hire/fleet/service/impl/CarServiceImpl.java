package ro.agilehub.javacourse.car.hire.fleet.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.fleet.exception.DuplicateFieldException;
import ro.agilehub.javacourse.car.hire.fleet.exception.DuplicateKeyMongoException;
import ro.agilehub.javacourse.car.hire.fleet.service.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.service.mapper.CarDOMapper;
import ro.agilehub.javacourse.car.hire.fleet.repository.CarRepository;
import ro.agilehub.javacourse.car.hire.fleet.repository.MakeRepository;
import ro.agilehub.javacourse.car.hire.fleet.service.CarService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final MakeRepository makeRepository;
    private final CarDOMapper mapper;
    private final ObjectMapper objectMapper;

    @Override
    public String addCar(CarDO carDO) {
        String registrationNumber = carDO.getRegistrationNumber();

        var carList = carRepository.findAllByRegistrationNumber(registrationNumber);
        if (carList.size() > 0) {
            log.error("A car with {} registration number exists already.", registrationNumber);
            throw new DuplicateFieldException("registrationNumber", registrationNumber, Car.COLLECTION_NAME);
        }

        try {
            var car = mapper.toCar(carDO);
            return carRepository.save(car)
                    .get_id()
                    .toString();
        } catch (DuplicateKeyException e) {
            log.error("Occurred a problem while save car in database, more details: {}", e.getCause().getMessage());
            throw new DuplicateKeyMongoException(e.getCause().getMessage());
        }
    }

    @Override
    public void removeCar(String id) {
        var car = carRepository
                .findById(new ObjectId(id))
                .orElseThrow();

        carRepository.delete(car);
        log.info("The car with {} id was deleted.", id);
    }

    @Override
    public CarDO findById(String id) {
        return carRepository
                .findById(new ObjectId(id))
                .map(this::map)
                .orElseThrow();
    }

    @Override
    public List<CarDO> findAll() {
        return carRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(toList());
    }

    @Override
    public CarDO updateCar(String id, List<ro.agilehub.javacourse.car.hire.fleet.service.model.JsonPatch> jsonPatch) throws JsonPatchException, JsonProcessingException {
        JsonPatch patch = objectMapper.convertValue(jsonPatch, JsonPatch.class);
        var car = carRepository.findById(new ObjectId(id)).orElseThrow();

        var carPatched = applyPatchToUser(patch, car);
        carPatched.set_id(car.get_id());

        log.info("The car has just been updated with id {}", carPatched.get_id());
        return map(carRepository.save(carPatched));
    }

    private Car applyPatchToUser(com.github.fge.jsonpatch.JsonPatch patch, Car targetCar) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCar, JsonNode.class));

        return objectMapper.treeToValue(patched, Car.class);
    }

    @Override
    public List<CarDO> findAllByStatus(String status) {
        return carRepository
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
