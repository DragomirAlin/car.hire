package ro.agilehub.javacourse.car.hire.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;
import ro.agilehub.javacourse.car.hire.MockMvcSetup;
import ro.agilehub.javacourse.car.hire.api.model.CreatedDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserResponseDTO;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.exception.HttpError;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("integrationtest")
public class UserControllerTests extends MockMvcSetup {

    @Before
    public void setupUserController() {
        countryService.saveCountry(Country.builder()
                .isoCode("ROU")
                .name("Romania")
                .build());
    }

    @Test
    @WithMockUser("jack")
    public void addUserOkTest() throws Exception {
        UserDTO userDTO = new UserDTO()
                .email("user1@carhire.ro")
                .driverLicense(152)
                .firstname("TestFirst")
                .lastname("TestSecond")
                .username("testusername1")
                .password("mypass")
                .title("mister")
                .country("ROU");

        MvcResult mvcResult = mvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated()).andReturn();


        String response = mvcResult.getResponse().getContentAsString();
        CreatedDTO createdDTO = objectMapper.readValue(response, CreatedDTO.class);

        var userResult = mvc.perform(get("/user/" + createdDTO.getId()))
                .andExpect(status().isOk())
                .andReturn();

        UserResponseDTO userResponse = objectMapper
                .readValue(userResult.getResponse().getContentAsString(), UserResponseDTO.class);

        assertEquals(userDTO.getEmail(), userResponse.getEmail());
        assertEquals(userDTO.getCountry(), userResponse.getCountry());
        assertEquals(userDTO.getDriverLicense(), userResponse.getDriverLicense());
        assertEquals(userDTO.getUsername(), userResponse.getUsername());
        assertEquals(userDTO.getLastname(), userResponse.getLastname());
        assertEquals(userDTO.getTitle(), userResponse.getTitle());
    }

    @Test
    @WithMockUser("jack")
    public void deleteUserTest() throws Exception {
        UserDTO userDTO = new UserDTO()
                .email("user2@carhire.ro");

        MvcResult mvcResult = mvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        CreatedDTO createdDTO = objectMapper.readValue(response, CreatedDTO.class);

        assertNotNull(createdDTO.getId());

        mvc.perform(delete("/user/" + createdDTO.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var exception = assertThrows(NestedServletException.class,
                () -> mvc.perform(get("/user/" + createdDTO.getId()))
                        .andExpect(status().isNotFound())
                        .andReturn());

        assertEquals(String.format("User is not found with ID : '%s'", createdDTO.getId()), exception.getCause().getMessage());
    }
}
