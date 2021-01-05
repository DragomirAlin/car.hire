package ro.agilehub.javacourse.car.hire.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import ro.agilehub.javacourse.car.hire.user.service.domain.UserDO;
import ro.agilehub.javacourse.car.hire.user.service.model.JsonPatch;

import java.util.List;

public interface UserService {

    String addUser(UserDO userDO);

    void removeUser(String id);

    UserDO findById(String id);

    List<UserDO> findAll();

    UserDO updateUser(String id, List<JsonPatch> jsonPatch) throws JsonPatchException, JsonProcessingException;

}
