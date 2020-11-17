package ro.agilehub.javacourse.car.hire.fleet.service;

import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.FleetDTO;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class FleetServiceImpl implements FleetService {

    private final ArrayList<CarDTO> carsList = new ArrayList<>();

    @Override
    public int addCar(CarDTO carDTO) {
        var id = carsList.size() + 1;
        carDTO.setId(id);
        carsList.add(carDTO);
        return id;
    }

    @Override
    public void removeCar(Integer id) {
        CarDTO user = getCar(id - 1);
        carsList.remove(user);
    }

    @Override
    public CarDTO getCar(Integer id) {
        CarDTO carDTO = carsList.stream().filter(car -> car.getId().equals(id)).findFirst().orElseThrow(() ->
                new NoSuchElementException("No user found with id " + id));
        return carDTO;
    }

    @Override
    public List<CarDTO> getCarsByStatus(String status) {
        return carsList.stream().filter(carDTO -> carDTO.getStatus().getValue().equals(status)).collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getCars() {
        return carsList;
    }

    @Override
    public CarDTO updateCar(Integer id, List<PatchDocument> patchDocument) {
        return getCar(id);
    }
}
