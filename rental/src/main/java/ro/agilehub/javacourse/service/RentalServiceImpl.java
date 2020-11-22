package ro.agilehub.javacourse.service;

import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.RentDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RentalServiceImpl  {

    private final ArrayList<RentDTO> rentalsList = new ArrayList<>();


    public int addRent(RentDTO rentDTO) {
        var id = rentalsList.size() + 1;
        rentDTO.setId(id);
        rentalsList.add(rentDTO);
        return id;
    }

    public void removeRent(Integer id) {
        RentDTO rent = getRent(id - 1);
        rentalsList.remove(rent);
    }

    public RentDTO getRent(Integer id) {
        RentDTO rentDTO = rentalsList.stream().filter(car -> car.getId().equals(id)).findFirst().orElseThrow(() ->
                new NoSuchElementException("No user found with id " + id));
        return rentDTO;
    }


    public List<RentDTO> getRents() {
        return rentalsList;
    }

    public RentDTO updateRent(Integer id, List<PatchDocument> patchDocument) {
        return getRent(id);
    }
}
