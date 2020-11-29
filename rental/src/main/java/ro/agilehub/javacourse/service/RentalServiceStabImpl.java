package ro.agilehub.javacourse.service;

import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.RentalDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RentalServiceStabImpl {

    private final ArrayList<RentalDTO> rentalsList = new ArrayList<>();


    public int addRent(RentalDTO rentDTO) {
        var id = rentalsList.size() + 1;
        rentDTO.setId(id);
        rentalsList.add(rentDTO);
        return id;
    }

    public void removeRent(Integer id) {
        RentalDTO rent = getRent(id - 1);
        rentalsList.remove(rent);
    }

    public RentalDTO getRent(Integer id) {
        RentalDTO rentDTO = rentalsList.stream().filter(car -> car.getId().equals(id)).findFirst().orElseThrow(() ->
                new NoSuchElementException("No user found with id " + id));
        return rentDTO;
    }


    public List<RentalDTO> getRents() {
        return rentalsList;
    }

    public RentalDTO updateRent(Integer id, List<PatchDocument> patchDocument) {
        return getRent(id);
    }
}
