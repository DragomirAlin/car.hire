package ro.agilehub.javacourse.car.hire.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.CreatedDTO;
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
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final CountryService countryService;
    private final UserDTOMapper mapper;
    private final JsonPatchDTOMapper jsonPatchDTOMapper;

    @Override
    public ResponseEntity<CreatedDTO> addUser(@Valid UserDTO userDTO) {
        var userDO = map(userDTO);
        var userID = userService.addUser(userDO);
        CreatedDTO createdDTO = new CreatedDTO();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdDTO.id(userID));
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
