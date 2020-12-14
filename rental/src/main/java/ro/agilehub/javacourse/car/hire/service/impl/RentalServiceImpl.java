package ro.agilehub.javacourse.car.hire.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.fleet.service.FleetService;
import ro.agilehub.javacourse.car.hire.service.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.service.domain.RentalDO;
import ro.agilehub.javacourse.car.hire.entity.Rental;
import ro.agilehub.javacourse.car.hire.service.mapper.RentalDOMapper;
import ro.agilehub.javacourse.car.hire.repository.RentalRepository;
import ro.agilehub.javacourse.car.hire.service.RentalService;
import ro.agilehub.javacourse.car.hire.service.UserService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalDOMapper mapper;
    @Autowired
    private UserService userService;
    @Autowired
    private FleetService fleetService;
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public String addRent(RentalDO rentalDO) {
        var rent = mapper.toRental(rentalDO);

        return rentalRepository.save(rent)
                .get_id()
                .toString();
    }

    @Override
    public void removeRent(String id) {
        var rent = rentalRepository
                .findById(new ObjectId(id))
                .orElseThrow();

        rentalRepository.delete(rent);
    }

    @Override
    public RentalDO findById(String id) {
        return rentalRepository
                .findById(new ObjectId(id))
                .map(this::map)
                .orElseThrow();

    }

    @Override
    public List<RentalDO> findAll() {
        return rentalRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(toList());
    }

    @Override
    public RentalDO updateRent(String id, List<JsonPatch> jsonPatch) throws JsonPatchException, JsonProcessingException {
        com.github.fge.jsonpatch.JsonPatch patch = objectMapper.convertValue(jsonPatch, com.github.fge.jsonpatch.JsonPatch.class);
        var rental = rentalRepository.findById(new ObjectId(id)).orElseThrow();

        var rentalPatched = applyPatchToUser(patch, rental);
        rentalPatched.set_id(rental.get_id());

        return map(rentalRepository.save(rentalPatched));
    }

    private Rental applyPatchToUser(com.github.fge.jsonpatch.JsonPatch patch, Rental targetRental) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetRental, JsonNode.class));

        return objectMapper.treeToValue(patched, Rental.class);
    }

    private RentalDO map(Rental rental) {
        var carDO = fleetService.findById(rental.getCar_id());
        var userDO = userService.findById(rental.getUser_id());

        return mapper.toRentalDO(rental, carDO, userDO);
    }

}
