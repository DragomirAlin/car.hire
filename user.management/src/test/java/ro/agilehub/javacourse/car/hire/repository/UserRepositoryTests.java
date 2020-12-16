package ro.agilehub.javacourse.car.hire.repository;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.agilehub.javacourse.car.hire.entity.User;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("test")
public class UserRepositoryTests {


    private static final String USERNAME = "Username";

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        var user = new User();
        user.set_id(new ObjectId("507f1f77bcf86cd799439011"));
        user.setUsername(USERNAME);
        userRepository.save(user);
    }

    @Test
    public void findByBy_whenReturn_match() {
        var foundUser = userRepository.findById(new ObjectId("507f1f77bcf86cd799439011"));

        assertTrue(foundUser.isPresent());
        assertEquals(USERNAME, foundUser.get().getUsername());
        assertEquals(new ObjectId("507f1f77bcf86cd799439011"), foundUser.get().get_id());
    }

}
