package ro.agilehub.javacourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.RentalDTO;
import ro.agilehub.javacourse.car.hire.api.specification.RentalApi;
import ro.agilehub.javacourse.service.RentalService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RentalController implements RentalApi {

    @Autowired
    private RentalService rentalService;

    @Override
    public ResponseEntity<String> addRental(@Valid RentalDTO rentalDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> cancelRental(String id) {
        return null;
    }

    @Override
    public ResponseEntity<RentalDTO> getRental(String id) {
        return null;
    }

    @Override
    public ResponseEntity<List<RentalDTO>> getRentals() {
        return null;
    }

    @Override
    public ResponseEntity<RentalDTO> updateRental(String id, @Valid List<PatchDocument> patchDocument) {
        return null;
    }
}
