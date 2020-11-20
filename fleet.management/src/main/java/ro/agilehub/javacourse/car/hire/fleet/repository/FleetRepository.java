package ro.agilehub.javacourse.car.hire.fleet.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.agilehub.javacourse.car.hire.fleet.entity.Fleet;

public interface FleetRepository extends MongoRepository<Fleet, ObjectId> {
}
