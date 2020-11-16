package ro.agilehub.javacourse.car.hire.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final ArrayList<UserDTO> usersList = new ArrayList<>();

    @Override
    public int addUser(UserDTO userDTO) {
        var id = usersList.size() + 1;
        userDTO.setId(id);
        usersList.add(userDTO);
        return id;
    }

    @Override
    public void removeUser(Integer id) {
        UserDTO user = getUser(id - 1);
        usersList.remove(user);
    }

    @Override
    public UserDTO getUser(Integer id) {
        UserDTO userDTO = usersList.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(() ->
                new NoSuchElementException("No user found with id " + id));
        return userDTO;
    }

    @Override
    public List<UserDTO> getUsers() {
        return usersList;
    }


    @Override
    public UserDTO updateUser(Integer id, JsonPatch jsonPatch) throws JsonPatchException {
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
        try {
            UserDTO userDTO = applyJson(jsonPatch, getUser(id), UserDTO.class);
            System.out.println(userDTO.getFirstname());
            System.out.println(userDTO.getEmail());
            return userDTO;
        }catch (JsonPatchException e){
            System.out.println(e);
            e.printStackTrace();
        }

        return null;
    }

    private UserDTO applyPatchToUser(com.github.fge.jsonpatch.JsonPatch patch, UserDTO targerUser) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targerUser, JsonNode.class));
        return objectMapper.treeToValue(patched, UserDTO.class);
    }

    private <T> T applyJson(JsonPatch jsonRequest, T targer, Class<T> clazz) throws JsonPatchException {
        com.github.fge.jsonpatch.JsonPatch jsonPatch = objectMapper.convertValue(jsonRequest, com.github.fge.jsonpatch.JsonPatch.class);
        JsonNode jsonNode = objectMapper.convertValue(targer, JsonNode.class);
        jsonNode = jsonPatch.apply(jsonNode);
        return objectMapper.convertValue(jsonNode, clazz);


    }
}
