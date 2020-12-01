package ro.agilehub.javacourse.car.hire.fleet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.FleetApi;
import ro.agilehub.javacourse.car.hire.fleet.mapper.CarDTOMapper;
import ro.agilehub.javacourse.car.hire.fleet.service.FleetService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class FleetController implements FleetApi {

    @Autowired
    private FleetService fleetService;

    @Autowired
    private CarDTOMapper mapper;

    @Override
    public ResponseEntity<String> addCar(@Valid CarDTO carDTO) {
        var carID = fleetService.addCar(carDTO);
        return ResponseEntity.ok(carID);
    }

    @Override
    public ResponseEntity<CarResponseDTO> getCar(String id) {
        var carResponseDTO = mapper.toCarResponseDTO(fleetService.findById(id));
        return ResponseEntity.ok(carResponseDTO);
    }

    @Override
    public ResponseEntity<List<CarResponseDTO>> getCars() {
        var listCarsResponseDTO = fleetService.findAll()
                .stream()
                .map(mapper::toCarResponseDTO)
                .collect(toList());

        return ResponseEntity.ok(listCarsResponseDTO);
    }

    @Override
    public ResponseEntity<List<CarResponseDTO>> getCarsByStatus(String status) {
        var listCarsResponseDTO = fleetService.findAllByStatus(status)
                .stream()
                .map(mapper::toCarResponseDTO)
                .collect(toList());

        return ResponseEntity.ok(listCarsResponseDTO);
    }

    @Override
    public ResponseEntity<Void> removeCar(String id) {
        fleetService.removeCar(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CarDTO> updateCar(String id, @Valid JsonPatch jsonPatch) {
        return null;
    }
}
