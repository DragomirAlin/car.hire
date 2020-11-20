package ro.agilehub.javacourse.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.agilehub.javacourse.entity.Rental;

public interface RentalRepository extends MongoRepository<Rental, ObjectId> {
}
