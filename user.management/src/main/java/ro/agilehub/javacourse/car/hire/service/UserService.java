package ro.agilehub.javacourse.car.hire.service;

import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.domain.UserDO;
import ro.agilehub.javacourse.car.hire.entity.User;

import java.util.List;

public interface UserService {

    String addUser(UserDTO userDTO);

    void removeUser(String id);

    UserDO findById(String id);

    List<UserDO> findAll();

    UserDO updateUser(String id, List<PatchDocument> patchDocument);

}
