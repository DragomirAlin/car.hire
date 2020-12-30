package ro.agilehub.javacourse.car.hire.service;

import org.bson.types.ObjectId;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ro.agilehub.javacourse.car.hire.entity.Country;
import ro.agilehub.javacourse.car.hire.entity.User;
import ro.agilehub.javacourse.car.hire.exception.NotFoundException;
import ro.agilehub.javacourse.car.hire.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.repository.UserRepository;
import ro.agilehub.javacourse.car.hire.service.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.service.domain.UserDO;
import ro.agilehub.javacourse.car.hire.service.impl.UserServiceImpl;
import ro.agilehub.javacourse.car.hire.service.mapper.CountryDOMapper;
import ro.agilehub.javacourse.car.hire.service.mapper.ObjectIdMapper;
import ro.agilehub.javacourse.car.hire.service.mapper.UserDOMapper;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private UserDOMapper userDOMapper;
    @Mock
    private CountryDOMapper countryDOMapper;
    @Mock
    private ObjectIdMapper objectIdMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("usernameTest");
        user.setEmail("test@carhire.ro");
        user.setCountry_id("507f1f77bcf86cd799439011");

        when(userDOMapper.toUser(any())).thenReturn(user);
        user.set_id(new ObjectId("111f1f77bcf86cd799439155"));
        when(userRepository.save(any())).thenReturn(user);

        String userId = userService.addUser(new UserDO());

        assertEquals("111f1f77bcf86cd799439155", userId);
    }

    @Test
    public void removeUser() {
        User user = new User();
        user.set_id(new ObjectId("555f1f77bcf86cd799439011"));

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        userService.removeUser("555f1f77bcf86cd799439011");
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void findById() {
        User user = new User();
        UserDO userDO = new UserDO();
        Country country = new Country();
        CountryDO countryDO = new CountryDO();

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(countryRepository.findById(any())).thenReturn(Optional.of(country));
        when(countryDOMapper.toCountryDO(any())).thenReturn(countryDO);
        when(userDOMapper.toUserDO(any(), any())).thenReturn(userDO);


        userService.findById("111f1f77bcf86cd799439155");

        verify(userDOMapper, times(1)).toUserDO(any(), any());

    }

    @Test
    public void findAll() {

    }

    @Test
    public void updateUser() {

    }


}

