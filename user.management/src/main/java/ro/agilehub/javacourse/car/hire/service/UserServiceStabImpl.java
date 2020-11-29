package ro.agilehub.javacourse.car.hire.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceStabImpl {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final ArrayList<UserDTO> usersList = new ArrayList<>();

    public int addUser(UserDTO userDTO) {
        var id = usersList.size() + 1;
        userDTO.setId(id);
        usersList.add(userDTO);
        return id;
    }

    public void removeUser(Integer id) {
        UserDTO user = getUser(id - 1);
        usersList.remove(user);
    }

    public UserDTO getUser(Integer id) {
        UserDTO userDTO = usersList.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(() ->
                new NoSuchElementException("No user found with id " + id));
        return userDTO;
    }

    public List<UserDTO> getUsers() {
        return usersList;
    }


    public UserDTO updateUser(Integer id, List<PatchDocument> patchDocument) {
        UserDTO userDTO = getUser(id);
        return userDTO;
    }

    private UserDTO applyPatchToUser(com.github.fge.jsonpatch.JsonPatch patch, UserDTO targerUser) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targerUser, JsonNode.class));
        return objectMapper.treeToValue(patched, UserDTO.class);
    }


}

