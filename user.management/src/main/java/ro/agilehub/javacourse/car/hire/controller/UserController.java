package ro.agilehub.javacourse.car.hire.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.specification.UserApi;
import ro.agilehub.javacourse.car.hire.exception.NotFoundException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController implements UserApi {

    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<UserDTO> usersList = new ArrayList<>();


    @Override
    public ResponseEntity<Integer> addUser(@Valid UserDTO userDTO) {
        var id = usersList.size() + 1;
        userDTO.setId(id);
        usersList.add(userDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDTO> getUser(Integer id) {
        Optional<UserDTO> user = getUserById(id);
        return ResponseEntity.ok(user.orElseThrow(NotFoundException::new));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(usersList);
    }

//    @Override
//    public ResponseEntity<UserDTO> updateUser(Integer id, ro.agilehub.javacourse.car.hire.api.model.@Valid JsonPatch jsonPatch) {
//        try {
//            UserDTO user = getUserById(id).orElseThrow(NotFoundException::new);
//            JsonPatch jsonp = JsonPatch.fromJson(jsonPatch);
//            UserDTO userPatched = applyPatchToUser(jsonPatch, user);
//            //update method
//            return ResponseEntity.ok().build();
//        } catch (JsonPatchException | JsonProcessingException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        } catch (NotFoundException | IOException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }

    @Override
    public ResponseEntity<Void> removeUser(Integer id) {
        usersList.remove(id - 1);
        return ResponseEntity.ok().build();
    }

    private Optional<UserDTO> getUserById(Integer id) {
        Optional<UserDTO> userDTO = usersList.stream().filter(user -> user.getId().equals(id)).findFirst();
        return userDTO;
    }

    private UserDTO applyPatchToUser(JsonPatch patch, UserDTO targerUser) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targerUser, JsonNode.class));
        return objectMapper.treeToValue(patched, UserDTO.class);
    }

}
