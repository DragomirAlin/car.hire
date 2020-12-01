package ro.agilehub.javacourse.car.hire.service;

import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.domain.UserDO;
import ro.agilehub.javacourse.car.hire.entity.User;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    String addUser(UserDO userDO);

    void removeUser(String id);

    UserDO findById(String id);

    List<UserDO> findAll();

    UserDO updateUser(String id, @Valid JsonPatch jsonPatch);

}
