package ro.agilehub.javacourse.car.hire.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserResponseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addUserOk() throws Exception {
        UserDTO userDTO = new UserDTO()
                .email("user@carhire.ro")
                .driverlicensenumber(152)
                .firstname("TestFirst")
                .lastname("TestSecond")
                .username("testusername")
                .password("mypass")
                .title("mister")
                .countryofresidence("RO");

        MvcResult mvcResult = mvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk()).andReturn();


        String response = mvcResult.getResponse().getContentAsString();
        UserResponseDTO userResponse = objectMapper.readValue(response, UserResponseDTO.class);

        var getResult = mvc.perform(get("/user/" + userResponse.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(userDTO.getEmail(), userResponse.getEmail());
        assertEquals(userDTO.getCountryofresidence(), userResponse.getCountryofresidence());
        assertEquals(userDTO.getDriverlicensenumber(), userDTO.getDriverlicensenumber());
        assertEquals(userDTO.getUsername(), userResponse.getUsername());
        assertEquals(userDTO.getLastname(), userResponse.getLastname());
        assertEquals(userDTO.getTitle(), userResponse.getTitle());

    }
}
