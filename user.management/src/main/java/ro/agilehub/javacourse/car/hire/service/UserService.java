package ro.agilehub.javacourse.car.hire.service;

import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.domain.UserDO;

import java.util.List;

public interface UserService {

    int addUser(UserDO userDO);

    void removeUser(Integer id);

    UserDO getUser(Integer id);

    List<UserDO> getUsers();

    UserDO updateUser(Integer id, List<PatchDocument> patchDocument);

}
