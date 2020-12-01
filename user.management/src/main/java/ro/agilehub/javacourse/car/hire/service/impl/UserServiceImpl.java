package ro.agilehub.javacourse.car.hire.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocument;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.domain.UserDO;
import ro.agilehub.javacourse.car.hire.entity.Country;
import ro.agilehub.javacourse.car.hire.entity.Status;
import ro.agilehub.javacourse.car.hire.entity.User;
import ro.agilehub.javacourse.car.hire.exception.NotFoundException;
import ro.agilehub.javacourse.car.hire.mapper.CountryDOMapper;
import ro.agilehub.javacourse.car.hire.mapper.UserDTOMapper;
import ro.agilehub.javacourse.car.hire.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.repository.UserRepository;
import ro.agilehub.javacourse.car.hire.mapper.UserDOMapper;
import ro.agilehub.javacourse.car.hire.service.UserService;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private UserDOMapper mapper;
    @Autowired
    private UserDTOMapper mapperDTO;

    @Override
    public String addUser(UserDTO userDTO) {
        var userDO = mapDTO(userDTO);
        var user = mapper.toUser(userDO);
        return userRepository.save(user).get_id().toString();
    }

    @Override
    public void removeUser(String id) {
        var user = userRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException(id));

        userRepository.delete(user);
    }

    @Override
    public UserDO findById(String id) {
        return userRepository.findById(new ObjectId(id))
                .map(this::map)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public List<UserDO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::map)
                .collect(toList());
    }

    @Override
    public UserDO updateUser(String id, List<PatchDocument> patchDocument) {

        return null;
    }

    private UserDO map(User user) {
        Country country = countryRepository
                .findById(new ObjectId(user.getCountry_id()))
                .orElse(null);

        return mapper.toUserDO(user, country);
    }

    private UserDO mapDTO(UserDTO userDTO) {
        var country = countryRepository.findByIsoCode(userDTO.getCountryofresidence())
                .orElse(null);

        return mapperDTO.toUserDO(userDTO, country);
    }

}
