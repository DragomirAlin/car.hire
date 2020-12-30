package ro.agilehub.javacourse.car.hire.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.exception.DuplicateFieldException;
import ro.agilehub.javacourse.car.hire.exception.DuplicateKeyMongoException;
import ro.agilehub.javacourse.car.hire.service.domain.UserDO;
import ro.agilehub.javacourse.car.hire.entity.Country;
import ro.agilehub.javacourse.car.hire.entity.User;
import ro.agilehub.javacourse.car.hire.exception.NotFoundException;
import ro.agilehub.javacourse.car.hire.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.repository.UserRepository;
import ro.agilehub.javacourse.car.hire.service.mapper.UserDOMapper;
import ro.agilehub.javacourse.car.hire.service.UserService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final UserDOMapper mapper;
    private final ObjectMapper objectMapper;

    @Override
    public String addUser(UserDO userDO) {
        String email = userDO.getEmail();
        String username = userDO.getUsername();

        var userEmailsList = userRepository.findAllByEmail(email);
        if (userEmailsList.size() > 0) throw new DuplicateFieldException("email", email, User.COLLECTION_NAME);

        var usernameList = userRepository.findAllByUsername(username);
        if (usernameList.size() > 0) throw new DuplicateFieldException("username", username, User.COLLECTION_NAME);

        try {
            var user = mapper.toUser(userDO);
            return userRepository.save(user)
                    .get_id()
                    .toString();
        } catch (DuplicateKeyException e) {
            log.info("Occured a problem while save user in database, more details: {}", e.getCause().getMessage());
            throw new DuplicateKeyMongoException(e.getCause().getMessage());
        }

    }

    @Override
    public void removeUser(String id) {
        var user = userRepository
                .findById(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException(id));

        userRepository.delete(user);
        log.info("User with {} id was deleted.", id);
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
    public UserDO updateUser(String id, List<ro.agilehub.javacourse.car.hire.service.model.JsonPatch> jsonPatch) throws JsonPatchException, JsonProcessingException {
        JsonPatch patch = objectMapper.convertValue(jsonPatch, JsonPatch.class);
        var user = userRepository.findById(new ObjectId(id)).orElseThrow();

        var userPatched = applyPatchToUser(patch, user);
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
