package ro.agilehub.javacourse.car.hire.service;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;
import ro.agilehub.javacourse.car.hire.entity.Country;
import ro.agilehub.javacourse.car.hire.entity.User;
import ro.agilehub.javacourse.car.hire.exception.DuplicateFieldException;
import ro.agilehub.javacourse.car.hire.exception.NotFoundException;
import ro.agilehub.javacourse.car.hire.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.repository.UserRepository;
import ro.agilehub.javacourse.car.hire.service.domain.UserDO;
import ro.agilehub.javacourse.car.hire.service.impl.UserServiceImpl;
import ro.agilehub.javacourse.car.hire.service.mapper.UserDOMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTests {

    public static final String OBJECT_ID = "555f1f77bcf86cd799439011";

    @Mock
    private UserRepository userRepository;
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private UserDOMapper userDOMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void getTest_addUser_whenOK() {
        User user = new User();
        user.setUsername("usernameTest");
        user.setEmail("test@carhire.ro");
        user.setCountry_id(OBJECT_ID);

        when(userDOMapper.toUser(any())).thenReturn(user);
        user.set_id(new ObjectId("111f1f77bcf86cd799439155"));
        when(userRepository.save(any())).thenReturn(user);

        String userId = userService.addUser(new UserDO());

        assertEquals("111f1f77bcf86cd799439155", userId);
    }

    @Test
    public void test_addUser_when_UsernameExists() {
        UserDO userDO = new UserDO();
        userDO.setEmail("test@carhire.ro");

        when(userRepository.findAllByEmail(any())).thenReturn(List.of(new User()));

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

        when(userRepository.findAllByUsername(any())).thenReturn(List.of(new User()));

        DuplicateFieldException exception = assertThrows(
                DuplicateFieldException.class,
                () -> userService.addUser(userDO));

        assertEquals("user", exception.getInput());
        assertEquals("username", exception.getFieldName());
    }

    @Test
    public void test_addUser_whenOccurredDuplicateKeyException() {
        when(userRepository.findAllByEmail(any())).thenReturn(List.of());
        when(userRepository.findAllByUsername(any())).thenReturn(List.of());

        UserDO userDO = mock(UserDO.class);
        User user = mock(User.class);
        DuplicateKeyException duplicateKeyException = mock(DuplicateKeyException.class);
        RuntimeException causeException = mock(RuntimeException.class);

        when(userDOMapper.toUser(userDO)).thenReturn(user);
        when(userRepository.save(user)).thenThrow(duplicateKeyException);

        assertThrows(DuplicateKeyException.class,
                () -> userService.addUser(userDO));
    }

    @Test
    public void test_removeUser_whenUserExists() {
        User user = new User();
        user.set_id(new ObjectId(OBJECT_ID));

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        userService.removeUser(OBJECT_ID);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void test_removeUser_whenUserNotExists() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> userService.removeUser(OBJECT_ID));
    }

    @Test
    public void test_getUser_whenUserExists() {
        UserDO userDO = UserDO.builder()
                .username("testuser")
                .build();
        User user = User.builder()
                .username("testuser")
                .build();
        when(userRepository.findById(new ObjectId(OBJECT_ID))).thenReturn(Optional.of(user));
        when(userRepository.findById(new ObjectId(OBJECT_ID)).map(any())).thenReturn(Optional.of(userDO));
        when(countryRepository.findById(new ObjectId(OBJECT_ID))).thenReturn(Optional.of(new Country()));
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

