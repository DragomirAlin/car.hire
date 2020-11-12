package ro.agilehub.javacourse.car.hire.controller;

import io.swagger.models.auth.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.InlineResponse201;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.specification.UserApi;
import ro.agilehub.javacourse.car.hire.exception.NotFoundException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController implements UserApi {

    ArrayList<UserDTO> usersList = new ArrayList<>();

    @Override
    public ResponseEntity<Integer> addUser(@Valid UserDTO userDTO) {
        var id = usersList.size() + 1;
        userDTO.setId(id);
        usersList.add(userDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDTO> getUser(Integer id) {
        Optional<UserDTO> user = usersList.stream().filter(userDTO -> userDTO.getId().equals(id)).findFirst();
        return ResponseEntity.ok(user.orElseThrow(NotFoundException::new));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(usersList);
    }

    @Override
    public ResponseEntity<Void> removeUser(Integer id) {
        usersList.remove(id - 1);
        return ResponseEntity.ok().build();
    }
}
