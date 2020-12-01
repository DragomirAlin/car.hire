package ro.agilehub.javacourse.car.hire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserResponseDTO;
import ro.agilehub.javacourse.car.hire.api.specification.UserApi;
import ro.agilehub.javacourse.car.hire.mapper.UserDTOMapper;
import ro.agilehub.javacourse.car.hire.service.UserService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDTOMapper mapper;


    @Override
    public ResponseEntity<String> addUser(@Valid UserDTO userDTO) {
        var userID = userService.addUser(userDTO);
        return ResponseEntity.ok(userID);
    }

    @Override
    public ResponseEntity<UserResponseDTO> getUser(String id) {
        var userResponseDTO = mapper
                .toUserResponseDTO(userService.findById(id));

        return ResponseEntity.ok(userResponseDTO);
    }

    @Override
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        var listUsersResponseDTO = userService.findAll()
                .stream()
                .map(mapper::toUserResponseDTO)
                .collect(toList());

        return ResponseEntity.ok(listUsersResponseDTO);
    }

    @Override
    public ResponseEntity<Void> removeUser(String id) {
        userService.removeUser(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(String id, @Valid JsonPatch patchDocument) {
        return null;
    }


}
