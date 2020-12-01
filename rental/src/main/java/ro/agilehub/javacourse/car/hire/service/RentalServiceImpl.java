package ro.agilehub.javacourse.car.hire.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.RentalDTO;
import ro.agilehub.javacourse.car.hire.fleet.repository.FleetRepository;
import ro.agilehub.javacourse.car.hire.fleet.service.FleetService;
import ro.agilehub.javacourse.car.hire.repository.UserRepository;
import ro.agilehub.javacourse.car.hire.domain.RentalDO;
import ro.agilehub.javacourse.car.hire.entity.Rental;
import ro.agilehub.javacourse.car.hire.mapper.RentalDOMapper;
import ro.agilehub.javacourse.car.hire.mapper.RentalDTOMapper;
import ro.agilehub.javacourse.car.hire.repository.RentalRepository;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalDOMapper mapper;
    @Autowired
    private RentalDTOMapper mapperDTO;
    @Autowired
    private UserService userService;
    @Autowired
    private FleetService fleetService;
    @Autowired
    private RentalDTOMapper rentalDTOMapper;


    @Override
    public String addRent(RentalDTO rentalDTO) {
        var rentDO = mapDTO(rentalDTO);
        var rent = mapper.toRental(rentDO);

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
    public RentalDO updateRent(String id, @Valid JsonPatch jsonPatch) {
        return null;
    }

    private RentalDO map(Rental rental) {
        var carDO = fleetService.findById(rental.getCar_id());
        var userDO = userService.findById(rental.getUser_id());

        return mapper.toRentalDO(rental, carDO, userDO);
    }

    private RentalDO mapDTO(RentalDTO rentalDTO) {
        var carDO = fleetService.findById(rentalDTO.getCar());
        var userDO = userService.findById(rentalDTO.getUser());

        return mapperDTO.toRentalDO(rentalDTO, carDO, userDO);
    }


}
