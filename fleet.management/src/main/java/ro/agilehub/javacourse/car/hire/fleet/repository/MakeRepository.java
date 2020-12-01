package ro.agilehub.javacourse.car.hire.fleet.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.entity.Make;

import java.util.Optional;

@Repository
public interface MakeRepository extends MongoRepository<Make, ObjectId> {

    Optional<Make> findByMakeName(String makeName);


}
