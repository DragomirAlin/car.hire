package ro.agilehub.javacourse.car.hire.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.agilehub.javacourse.car.hire.entity.Country;

import java.util.Optional;

@Repository
public interface CountryRepository extends MongoRepository<Country, ObjectId> {

    Optional<Country> findByIsoCode(String isoCode);
}
