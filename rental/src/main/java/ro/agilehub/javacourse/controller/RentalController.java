package ro.agilehub.javacourse.controller;

import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.RentDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.specification.RentApi;
import ro.agilehub.javacourse.service.RentalService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RentalController implements RentApi {

    @Autowired
    private RentalService rentalService;

    @Override
    public ResponseEntity<Integer> addRent(@Valid RentDTO rentDTO) {
        var id = rentalService.addRent(rentDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RentDTO> getRent(Integer id) {
        RentDTO rentDTO = rentalService.getRent(id);
        return ResponseEntity.ok(rentDTO);
    }

    @Override
    public ResponseEntity<List<RentDTO>> getRents() {
        return ResponseEntity.ok(rentalService.getRents());
    }

    @Override
    public ResponseEntity<Void> removeRent(Integer id) {
        rentalService.removeRent(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RentDTO> updateRent(Integer id, @Valid List<PatchDocument> patchDocument) {
        return null;
    }
}
