package ro.agilehub.javacourse.car.hire.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.agilehub.javacourse.car.hire.entity.Rental;

public interface RentalRepository extends MongoRepository<Rental, ObjectId> {
}
