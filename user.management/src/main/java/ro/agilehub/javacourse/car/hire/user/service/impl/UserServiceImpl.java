package ro.agilehub.javacourse.car.hire.user.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.user.entity.Status;
import ro.agilehub.javacourse.car.hire.user.exception.DuplicateFieldException;
import ro.agilehub.javacourse.car.hire.user.exception.DuplicateKeyMongoException;
import ro.agilehub.javacourse.car.hire.user.exception.HttpError;
import ro.agilehub.javacourse.car.hire.user.service.domain.UserDO;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.entity.User;
import ro.agilehub.javacourse.car.hire.user.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.user.repository.UserRepository;
import ro.agilehub.javacourse.car.hire.user.service.mapper.UserDOMapper;
import ro.agilehub.javacourse.car.hire.user.service.UserService;

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

        if (userEmailsList.size() > 0) {
            log.error("An user with {} email exists already!", email);
            throw HttpError.badRequest(String.format("An user with %s email exists already!", email));
        }

        var usernameList = userRepository.findAllByUsername(username);
        if (usernameList.size() > 0) {
            log.error("An user with {} username exists already!", username);
            throw HttpError.badRequest(String.format("An user with %s username exists already!", username));
        }

        try {
            var user = mapper.toUser(userDO);
            var userCreated = userRepository.save(user);

            log.info("The user with id {} has just been created.", user.get_id().toString());
            return userCreated.get_id()
                    .toString();
        } catch (DuplicateKeyException e) {
            log.error("Occurred a problem while save user in database, more details: {}!", e.getCause().getMessage());
            throw HttpError.badRequest(String.format("Occurred a problem while save %s user in database.", userDO.getId()));
        }

    }

    @Override
    public void removeUser(String id) {
        var user = userRepository
                .findById(new ObjectId(id))
                .orElseThrow(() -> HttpError.notFound(String.format("User is not found with ID : '%s'", id)));

        userRepository.delete(user);
        log.info("User with {} id was deleted.", id);
    }

    @Override
    public UserDO findById(String id) {
        return userRepository
                .findById(new ObjectId(id))
                .map(this::map)
                .orElseThrow(() -> HttpError.notFound(String.format("User is not found with ID : '%s'", id)));
    }

    @Override
    public List<UserDO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::map)
                .collect(toList());
    }

    @Override
    public UserDO updateUser(String id, List<ro.agilehub.javacourse.car.hire.user.service.model.JsonPatch> jsonPatch) throws JsonPatchException, JsonProcessingException {
        JsonPatch patch = objectMapper.convertValue(jsonPatch, JsonPatch.class);
        var user = userRepository.findById(new ObjectId(id)).orElseThrow();

        var userPatched = applyPatchToUser(patch, user);
        userPatched.set_id(user.get_id());

        log.info("The user has just been updated with id {}.", userPatched.get_id());
        return map(userRepository.save(userPatched));
    }

    private User applyPatchToUser(JsonPatch patch, User targetCustomer) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));

        return objectMapper.treeToValue(patched, User.class);
    }

    private UserDO map(User user) {
        Country country = countryRepository
                .findById(new ObjectId(user.getCountryId()))
                .orElse(null);

        return mapper.toUserDO(user, country);
    }

}
