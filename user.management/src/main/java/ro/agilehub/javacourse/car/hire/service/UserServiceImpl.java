package ro.agilehub.javacourse.car.hire.service;

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
import ro.agilehub.javacourse.car.hire.mapper.CountryDOMapper;
import ro.agilehub.javacourse.car.hire.mapper.UserDTOMapper;
import ro.agilehub.javacourse.car.hire.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.repository.UserRepository;
import ro.agilehub.javacourse.car.hire.mapper.UserDOMapper;

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

    @Autowired
    private CountryDOMapper countryDOMapper;


    @Override
    public String addUser(UserDTO userDTO) {
        UserDO userDO = mapDTO(userDTO);

        User user = mapper.toUser(userDO, userDO.getCountryOfResidence(), userDO.getStatus());
        return userRepository.save(user).get_id().toString();
    }

    @Override
    public void removeUser(String id) {
        userRepository.deleteById(new ObjectId(id));
    }

    @Override
    public UserDO findById(String id) {
        return userRepository.findById(new ObjectId(id))
                .map(this::map)
                .orElseThrow();
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
                .findById(new ObjectId(user.getCountry()))
                .orElse(null);

        return mapper.toUserDO(user, country, Status.valueOf(user.getStatus()));
    }

    private UserDO mapDTO(UserDTO userDTO) {
        var country = countryRepository.findByIsoCode(userDTO.getCountryofresidence())
                .orElse(null);

        var countryDO = countryDOMapper.toCountryDO(country);

        return mapperDTO.toUserDO(userDTO, countryDO, Status.ACTIVE);
    }

}
