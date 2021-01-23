package ro.agilehub.javacourse.car.hire.user.service;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;
import ro.agilehub.javacourse.car.hire.BaseTestSetup;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.entity.Status;
import ro.agilehub.javacourse.car.hire.user.entity.User;
import ro.agilehub.javacourse.car.hire.user.exception.DuplicateFieldException;
import ro.agilehub.javacourse.car.hire.user.exception.HttpError;
import ro.agilehub.javacourse.car.hire.user.service.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.user.service.domain.UserDO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests extends BaseTestSetup {

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
        UserDO userDO = new UserDO();
        userDO.setEmail("test@carhire.ro");

        DuplicateFieldException exception = assertThrows(
                DuplicateFieldException.class,
                () -> userService.addUser(userDO));

        assertEquals("test@carhire.ro", exception.getInput());
        assertEquals("email", exception.getFieldName());
    }

    @Test
    public void test_addUser_whenEmailExists() {
        UserDO userDO = new UserDO();
        userDO.setUsername("user");

        DuplicateFieldException exception = assertThrows(
                DuplicateFieldException.class,
                () -> userService.addUser(userDO));

        assertEquals("user", exception.getInput());
        assertEquals("username", exception.getFieldName());
    }

    @Test
    public void test_addUser_whenOccurredDuplicateKeyException() {

        UserDO userDO = mock(UserDO.class);
        User user = mock(User.class);
        DuplicateKeyException duplicateKeyException = mock(DuplicateKeyException.class);
        RuntimeException causeException = mock(RuntimeException.class);

        assertThrows(DuplicateKeyException.class,
                () -> userService.addUser(userDO));
    }

    @Test
    public void test_removeUser_whenUserExists() {
        User user = User.builder()
                ._id(new ObjectId(OBJECT_ID))
                .build();


        userService.removeUser(OBJECT_ID);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void test_removeUser_whenUserNotExists() {
        assertThrows(HttpError.class,
                () -> userService.removeUser(OBJECT_ID));
    }

    @Test
    public void test_getUser_whenUserExists() {


        UserDO userResult = userService.findById(OBJECT_ID);

        assertEquals("testuser", userResult.getUsername());
    }


    @Test
    public void findAll() {

    }

    @Test
    public void updateUser() {

    }


}

