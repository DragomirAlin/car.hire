package ro.agilehub.javacourse.car.hire.service.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import io.swagger.v3.core.util.Json;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
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
    private UserDTOMapper mapperDTO;

    private ObjectMapper objectMapper;

    @Override
    public String addUser(UserDTO userDTO) {
        var userDO = mapDTO(userDTO);
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
    public UserDO updateUser(String id, @Valid ro.agilehub.javacourse.car.hire.api.model.JsonPatch jsonPatch) {
        try {
            User user = userRepository.findById(new ObjectId(id)).orElseThrow();
            JsonNode jsonNode = objectMapper.readTree(String.valueOf(jsonPatch));
            JsonPatch jsonPatchs = JsonPatch.fromJson(jsonNode);
            User userPatched = applyPatchToUser(jsonPatchs, user);
            userRepository.save(userPatched);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonPatchException e) {
            e.printStackTrace();
        }
        var userDO = map(userRepository.findById(new ObjectId(id)).orElseThrow());
        return userDO;
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

    private UserDO mapDTO(UserDTO userDTO) {
        var country = countryRepository
                .findByIsoCode(userDTO.getCountryofresidence())
                .orElse(null);

        return mapperDTO.toUserDO(userDTO, country);
    }

}
