package ro.agilehub.javacourse.car.hire.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.domain.UserDO;
import ro.agilehub.javacourse.car.hire.entity.Country;
import ro.agilehub.javacourse.car.hire.entity.User;
import ro.agilehub.javacourse.car.hire.exception.NotFoundException;
import ro.agilehub.javacourse.car.hire.mapper.UserDTOMapper;
import ro.agilehub.javacourse.car.hire.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.repository.UserRepository;
import ro.agilehub.javacourse.car.hire.mapper.UserDOMapper;
import ro.agilehub.javacourse.car.hire.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private UserDOMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String addUser(UserDO userDO) {
        var user = mapper.toUser(userDO);

        return userRepository.save(user)
                .get_id()
                .toString();
    }

    @Override
    public void removeUser(String id) {
        var user = userRepository
                .findById(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException(id));

        userRepository.delete(user);
    }

    @Override
    public UserDO findById(String id) {
        return userRepository
                .findById(new ObjectId(id))
                .map(this::map)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public List<UserDO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::map)
                .collect(toList());
    }

    @Override
    public UserDO updateUser(String id, @Valid List<ro.agilehub.javacourse.car.hire.api.model.JsonPatch> jsonPatch) throws JsonPatchException, JsonProcessingException {
        JsonPatch patch = objectMapper.convertValue(jsonPatch, JsonPatch.class);
        User user = userRepository.findById(new ObjectId(id)).orElseThrow();

        User userPatched = applyPatchToUser(patch, user);
        userPatched.set_id(user.get_id());
        return map(userRepository.save(userPatched));
    }

    private User applyPatchToUser(JsonPatch patch, User targetCustomer) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }

    private UserDO map(User user) {
        Country country = countryRepository
                .findById(new ObjectId(user.getCountry_id()))
                .orElse(null);

        return mapper.toUserDO(user, country);
    }

}
