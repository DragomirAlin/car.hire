package ro.agilehub.javacourse.car.hire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.domain.UserDO;
import ro.agilehub.javacourse.car.hire.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public int addUser(UserDO userDO) {
        return 0;
    }

    @Override
    public void removeUser(Integer id) {

    }

    @Override
    public UserDO getUser(Integer id) {
        return null;
    }

    @Override
    public List<UserDO> getUsers() {
        return null;
    }

    @Override
    public UserDO updateUser(Integer id, List<PatchDocument> patchDocument) {
        return null;
    }
}
