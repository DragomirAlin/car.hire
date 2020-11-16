package ro.agilehub.javacourse.service;

import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.api.model.RentDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RentalServiceImpl implements RentalService {

    private final ArrayList<RentDTO> rentalsList = new ArrayList<>();


    @Override
    public int addRent(RentDTO rentDTO) {
        var id = rentalsList.size() + 1;
        rentDTO.setId(id);
        rentalsList.add(rentDTO);
        return id;
    }

    @Override
    public void removeRent(Integer id) {
        RentDTO rent = getRent(id - 1);
        rentalsList.remove(rent);
    }

    @Override
    public RentDTO getRent(Integer id) {
        RentDTO rentDTO = rentalsList.stream().filter(car -> car.getId().equals(id)).findFirst().orElseThrow(() ->
                new NoSuchElementException("No user found with id " + id));
        return rentDTO;
    }

    @Override
    public List<RentDTO> getRents() {
        return rentalsList;
    }

    @Override
    public RentDTO updateRent(Integer id, JsonPatch jsonPatch) {
        return null;
    }
}
