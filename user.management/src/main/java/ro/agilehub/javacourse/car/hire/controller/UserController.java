package ro.agilehub.javacourse.car.hire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.specification.UserApi;
import ro.agilehub.javacourse.car.hire.service.UserService;

import javax.validation.Valid;
import java.util.List;


@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;


    @Override
    public ResponseEntity<Integer> addUser(@Valid UserDTO userDTO) {
        var id = userService.addUser(userDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDTO> getUser(Integer id) {
        UserDTO user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @Override
    public ResponseEntity<Void> removeUser(Integer id) {
        userService.removeUser(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDTO>  updateUser(Integer id, @Valid List<PatchDocument> patchDocument)  {
            return ResponseEntity.ok(userService.updateUser(id, patchDocument));
    }


}
