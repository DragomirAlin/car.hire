package ro.agilehub.javacourse.car.hire.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.entity.Status;
import ro.agilehub.javacourse.car.hire.user.exception.HttpError;
import ro.agilehub.javacourse.car.hire.user.service.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.user.service.domain.UserDO;
import ro.agilehub.javacourse.car.hire.user.service.model.JsonPatch;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private CountryService countryService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserService userService;

    private static final String OBJECT_ID = "555f1f77bcf86cd799439011";
    private CountryDO countryDO;

    @Before
    public void setupUserTests() {
        var countryDO = countryService.saveCountry(Country.builder()
                .isoCode("ROU")
                .name("Romania")
                .build());

        assertNotNull(countryDO);
        this.countryDO = countryDO;
    }

    @After
    public void dropDatabase() {
        mongoTemplate.getDb().drop();
    }

    @Test
    public void addUser_thenGet_whenOK() {
        UserDO userDO = UserDO.builder()
                .username("usertest")
                .countryDO(countryDO)
                .status(Status.ACTIVE)
                .build();

        String userId = userService.addUser(userDO);

        assertNotNull(userId);

        var userDOFound = userService.findById(userId);

        assertEquals(userDO.getUsername(), userDOFound.getUsername());
        assertEquals(countryDO.getId(), userDOFound.getCountryDO().getId());
    }

    @Test
    public void test_addUser_when_UsernameExists() {
        userService.addUser(UserDO.builder()
                .username("user")
                .email("user43@carhire.ro")
                .build());

        UserDO userDO = UserDO.builder()
                .email("user543@carhire.ro")
                .username("user")
                .build();

        var exception = assertThrows(
                HttpError.class,
                () -> userService.addUser(userDO));

        assertEquals("An user with user username exists already!", exception.getMessage());
    }

    @Test
    public void test_addUser_whenEmailExists() {
        userService.addUser(UserDO.builder()
                .username("user2")
                .email("user@carhire.ro")
                .build());

        UserDO userDO = UserDO.builder()
                .username("user3")
                .email("user@carhire.ro")
                .build();

        var exception = assertThrows(
                HttpError.class,
                () -> userService.addUser(userDO));

        assertEquals("An user with user@carhire.ro email exists already!", exception.getMessage());
    }

    @Test
    public void test_removeUser_whenUserExists() {
        var userId = userService.addUser(UserDO.builder()
                .username("user2")
                .email("user@carhire.ro")
                .countryDO(countryDO)
                .build());

        var userDO = userService.findById(userId);

        assertNotNull(userDO);

        userService.removeUser(userId);

        var exception = assertThrows(HttpError.class,
                () -> userService.findById(userId)
        );

        assertEquals(String.format("User is not found with ID : '%s'", userId), exception.getMessage());

    }

    @Test
    public void test_removeUser_whenUserNotExists() {
        var exception = assertThrows(HttpError.class,
                () -> userService.removeUser(OBJECT_ID));

        assertEquals("User is not found with ID : '" + OBJECT_ID + "'", exception.getMessage());
    }

    @Test
    public void findAll() {
        userService.addUser(UserDO.builder()
                .username("user64")
                .email("user34@carhire.ro")
                .countryDO(countryDO)
                .build());

        userService.addUser(UserDO.builder()
                .username("user36")
                .email("user43@carhire.ro")
                .countryDO(countryDO)
                .build());

        userService.addUser(UserDO.builder()
                .username("user44")
                .email("user33@carhire.ro")
                .countryDO(countryDO)
                .build());

        var usersDO = userService.findAll();

        assertEquals(3, usersDO.size());

    }

    @Test
    public void updateUser() throws JsonPatchException, JsonProcessingException {
        var userId = userService.addUser(UserDO.builder()
                .username("user64")
                .email("user34@carhire.ro")
                .countryDO(countryDO)
                .status(Status.ACTIVE)
                .build());

        List<JsonPatch> patchList = List.of(JsonPatch.builder()
                .op("replace")
                .path("/status")
                .value("CANCELLED")
                .build());

        var userDOUpdated = userService.updateUser(userId, patchList);

        assertEquals(Status.CANCELLED, userDOUpdated.getStatus());
    }


}

