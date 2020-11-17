package ro.agilehub.javacourse.car.hire.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.JsonPatch;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;

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
    public UserDTO updateUser(Integer id, List<PatchDocument> patchDocument) {
      UserDTO userDTO = getUser(id);
      return userDTO;
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
