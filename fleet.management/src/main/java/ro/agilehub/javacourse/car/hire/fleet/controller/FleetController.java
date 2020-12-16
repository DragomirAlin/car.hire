package ro.agilehub.javacourse.car.hire.fleet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.FleetApi;
import ro.agilehub.javacourse.car.hire.fleet.service.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.service.mapper.CarDTOMapper;
import ro.agilehub.javacourse.car.hire.fleet.service.FleetService;
import ro.agilehub.javacourse.car.hire.fleet.service.MakeService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class FleetController implements FleetApi {

    private final FleetService fleetService;
    private final MakeService makeService;
    private final CarDTOMapper mapper;

    @Override
    public ResponseEntity<CreatedDTO>addCar(@Valid CarDTO carDTO) {
        var carDO = map(carDTO);
        var carID = fleetService.addCar(carDO);
        CreatedDTO createdDTO = new CreatedDTO();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdDTO.id(carID));
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
    public ResponseEntity<CarDTO> updateCar(String id, @Valid List<JsonPatchDTO> jsonPatchDTO) {
        return null;
    }

    private CarDO map(CarDTO carDTO) {
        var makeDO = makeService.findByMakeName(carDTO.getMake());

        return mapper.toCarDO(carDTO, makeDO);
    }

}
