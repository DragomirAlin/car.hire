package ro.agilehub.javacourse.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.RentalDTO;
import ro.agilehub.javacourse.car.hire.fleet.repository.FleetRepository;
import ro.agilehub.javacourse.car.hire.repository.UserRepository;
import ro.agilehub.javacourse.domain.RentalDO;
import ro.agilehub.javacourse.entity.Rental;
import ro.agilehub.javacourse.mapper.RentalDOMapper;
import ro.agilehub.javacourse.mapper.RentalDTOMapper;
import ro.agilehub.javacourse.repository.RentalRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FleetRepository fleetRepository;
    @Autowired
    private RentalDOMapper mapper;
    @Autowired
    private RentalDTOMapper mapperDTO;


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
    public RentalDO updateRent(String id, List<PatchDocument> patchDocument) {
        return null;
    }

    private RentalDO map(Rental rental) {
        var car = fleetRepository
                .findById(new ObjectId(rental.getCar_id()))
                .orElseThrow();
        var user = userRepository
                .findById(new ObjectId(rental.getUser_id()))
                .orElseThrow();

        return mapper.toRentalDO(rental, car, user);
    }

    private RentalDO mapDTO(RentalDTO rentalDTO) {
        var car = fleetRepository
                .findById(new ObjectId(rentalDTO.getCar()))
                .orElseThrow();
        var user = userRepository
                .findById(new ObjectId(rentalDTO.getUser()))
                .orElseThrow();

        return mapperDTO.toRentalDO(rentalDTO, car, user);
    }


}
