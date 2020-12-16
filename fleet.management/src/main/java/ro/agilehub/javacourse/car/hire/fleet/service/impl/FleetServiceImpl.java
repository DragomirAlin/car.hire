package ro.agilehub.javacourse.car.hire.fleet.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.fleet.service.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.service.mapper.CarDOMapper;
import ro.agilehub.javacourse.car.hire.fleet.repository.FleetRepository;
import ro.agilehub.javacourse.car.hire.fleet.repository.MakeRepository;
import ro.agilehub.javacourse.car.hire.fleet.service.FleetService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class FleetServiceImpl implements FleetService {

    private final FleetRepository fleetRepository;
    private final MakeRepository makeRepository;
    private final CarDOMapper mapper;
    private final ObjectMapper objectMapper;

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
    public CarDO updateCar(String id, List<ro.agilehub.javacourse.car.hire.fleet.service.model.JsonPatch> jsonPatch) throws JsonPatchException, JsonProcessingException {
        JsonPatch patch = objectMapper.convertValue(jsonPatch, JsonPatch.class);
        var car = fleetRepository.findById(new ObjectId(id)).orElseThrow();

        var carPatched = applyPatchToUser(patch, car);
        carPatched.set_id(car.get_id());

        return map(fleetRepository.save(carPatched));
    }

    private Car applyPatchToUser(com.github.fge.jsonpatch.JsonPatch patch, Car targetCar) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCar, JsonNode.class));

        return objectMapper.treeToValue(patched, Car.class);
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
