package ro.agilehub.javacourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.RentalDTO;
import ro.agilehub.javacourse.car.hire.api.model.RentalResponseDTO;
import ro.agilehub.javacourse.car.hire.api.specification.RentalApi;
import ro.agilehub.javacourse.mapper.RentalDTOMapper;
import ro.agilehub.javacourse.service.RentalService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class RentalController implements RentalApi {

    @Autowired
    private RentalService rentalService;
    @Autowired
    private RentalDTOMapper mapper;

    @Override
    public ResponseEntity<String> addRental(@Valid RentalDTO rentalDTO) {
        var rentalID = rentalService.addRent(rentalDTO);
        return ResponseEntity.ok(rentalID);
    }

    @Override
    public ResponseEntity<Void> cancelRental(String id) {
        rentalService.removeRent(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RentalResponseDTO> getRental(String id) {
        var rentalResponseDTO = mapper
                .toRentalResponseDTO(rentalService.findById(id));

        return ResponseEntity.ok(rentalResponseDTO);
    }

    @Override
    public ResponseEntity<List<RentalResponseDTO>> getRentals() {
        var listRentalsReponseDTO = rentalService.findAll()
                .stream()
                .map(mapper::toRentalResponseDTO)
                .collect(toList());

        return ResponseEntity.ok(listRentalsReponseDTO);
    }

    @Override
    public ResponseEntity<RentalDTO> updateRental(String id, @Valid List<PatchDocument> patchDocument) {
        return null;
    }
}
