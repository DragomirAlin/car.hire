package ro.agilehub.javacourse.car.hire.service;

import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    int addUser(UserDTO userDTO);
    void removeUser(Integer id);
    UserDTO getUser(Integer id);
    List<UserDTO> getUsers();
    UserDTO updateUser(Integer id, JsonPatch jsonPatch);

}
