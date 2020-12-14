package ro.agilehub.javacourse.car.hire.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatchDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserResponseDTO;
import ro.agilehub.javacourse.car.hire.api.specification.UserApi;
import ro.agilehub.javacourse.car.hire.service.domain.UserDO;
import ro.agilehub.javacourse.car.hire.service.mapper.JsonPatchDTOMapper;
import ro.agilehub.javacourse.car.hire.service.mapper.UserDTOMapper;
import ro.agilehub.javacourse.car.hire.service.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.service.CountryService;
import ro.agilehub.javacourse.car.hire.service.UserService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private UserDTOMapper mapper;
    @Autowired
    private JsonPatchDTOMapper jsonPatchDTOMapper;


    @Override
    public ResponseEntity<String> addUser(@Valid UserDTO userDTO) {
        var userDO = map(userDTO);
        var userID = userService.addUser(userDO);
        return ResponseEntity.ok(userID);
    }

    @Override
    public ResponseEntity<UserResponseDTO> getUser(String id) {
        var userResponseDTO = mapper
                .toUserResponseDTO(userService.findById(id));

        return ResponseEntity.ok(userResponseDTO);
    }

    @Override
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        var listUsersResponseDTO = userService.findAll()
                .stream()
                .map(mapper::toUserResponseDTO)
                .collect(toList());

        return ResponseEntity.ok(listUsersResponseDTO);
    }

    @Override
    public ResponseEntity<Void> removeUser(String id) {
        userService.removeUser(id);
        return ResponseEntity.ok().build();
    }

    private UserDO map(UserDTO userDTO) {
        var countryDO = countryService.findByIsoCode(userDTO.getCountryofresidence());

        return mapper.toUserDO(userDTO, countryDO);
    }

    public ResponseEntity<Void> updateUser(String id, @Valid List<JsonPatchDTO> jsonPatch)  {
        List<JsonPatch> jsonPatchList = jsonPatch.stream().map(jsonPatchDTOMapper::toJsonPatch).collect(toList());
        UserDO userDO = null;
        try {
            userDO = userService.updateUser(id, jsonPatchList);
        } catch (JsonPatchException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }
}
