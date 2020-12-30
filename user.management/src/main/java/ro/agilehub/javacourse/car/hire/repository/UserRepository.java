package ro.agilehub.javacourse.car.hire.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.agilehub.javacourse.car.hire.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    List<User> findAllByEmail(String email);
    List<User> findAllByUsername(String username);
}
