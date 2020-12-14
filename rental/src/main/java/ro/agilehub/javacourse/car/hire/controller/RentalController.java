package ro.agilehub.javacourse.car.hire.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatchDTO;
import ro.agilehub.javacourse.car.hire.api.model.RentalDTO;
import ro.agilehub.javacourse.car.hire.api.model.RentalResponseDTO;
import ro.agilehub.javacourse.car.hire.api.specification.RentalApi;
import ro.agilehub.javacourse.car.hire.service.domain.RentalDO;
import ro.agilehub.javacourse.car.hire.fleet.service.FleetService;
import ro.agilehub.javacourse.car.hire.service.mapper.JsonPatchDTOMapper;
import ro.agilehub.javacourse.car.hire.service.mapper.JsonPatchDTORentalMapper;
import ro.agilehub.javacourse.car.hire.service.mapper.RentalDTOMapper;
import ro.agilehub.javacourse.car.hire.service.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.service.RentalService;
import ro.agilehub.javacourse.car.hire.service.UserService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class RentalController implements RentalApi {

    @Autowired
    private RentalService rentalService;
    @Autowired
    private RentalDTOMapper mapper;
    @Autowired
    private UserService userService;
    @Autowired
    private FleetService fleetService;
    @Autowired
    private JsonPatchDTORentalMapper mapperJsonPatch;

    @Override
    public ResponseEntity<String> addRental(@Valid RentalDTO rentalDTO) {
        var rentalDO = map(rentalDTO);
        var rentalID = rentalService.addRent(rentalDO);
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
    public ResponseEntity<RentalDTO> updateRental(String id, @Valid List<JsonPatchDTO> jsonPatchDTO) {
        List<JsonPatch> jsonPatchList = jsonPatchDTO.stream().map(mapperJsonPatch::toJsonPatch).collect(toList());
        RentalDO rentalDO = null;
        try {
            rentalDO = rentalService.updateRent(id, jsonPatchList);
        } catch (JsonPatchException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    private RentalDO map(RentalDTO rentalDTO) {
        var carDO = fleetService.findById(rentalDTO.getCar());
        var userDO = userService.findById(rentalDTO.getUser());

        return mapper.toRentalDO(rentalDTO, carDO, userDO);
    }


}