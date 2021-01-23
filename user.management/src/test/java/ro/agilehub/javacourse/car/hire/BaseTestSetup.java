package ro.agilehub.javacourse.car.hire;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import ro.agilehub.javacourse.car.hire.user.repository.UserRepository;
import ro.agilehub.javacourse.car.hire.user.service.CountryService;
import ro.agilehub.javacourse.car.hire.user.service.UserService;


public class BaseTestSetup {
    @Autowired
    protected CountryService countryService;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected MongoTemplate mongoTemplate;
    @Autowired
    protected UserService userService;
    @Autowired
    protected UserRepository userRepository;

    @After
    public void dropDatabase() {
        mongoTemplate.getDb().drop();
    }
}


