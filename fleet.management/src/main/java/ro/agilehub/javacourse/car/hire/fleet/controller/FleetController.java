package ro.agilehub.javacourse.car.hire.fleet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.FleetApi;
import ro.agilehub.javacourse.car.hire.fleet.service.FleetService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FleetController implements FleetApi {

    @Autowired
    private FleetService fleetService;

    @Override
    public ResponseEntity<Integer> addCar(@Valid CarDTO carDTO) {
        var id = fleetService.addCar(carDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CarDTO> getCar(Integer id) {
        CarDTO carDTO = fleetService.getCar(id);
        return ResponseEntity.ok(carDTO);
    }

    @Override
    public ResponseEntity<List<CarDTO>> getCars() {
        return ResponseEntity.ok(fleetService.getCars());
    }

    @Override
    public ResponseEntity<Void> removeCar(Integer id) {
        fleetService.removeCar(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CarDTO> updateCar(Integer id, @Valid List<PatchDocument> patchDocument) {
        return null;
    }
}
