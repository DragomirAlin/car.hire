package ro.agilehub.javacourse.car.hire.fleet.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;

public interface FleetRepository extends MongoRepository<Car, ObjectId> {
}
