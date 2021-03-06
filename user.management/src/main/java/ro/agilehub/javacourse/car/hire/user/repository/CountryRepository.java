package ro.agilehub.javacourse.car.hire.user.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.agilehub.javacourse.car.hire.user.entity.Country;

import java.util.Optional;

@Repository
public interface CountryRepository extends MongoRepository<Country, ObjectId> {

    Optional<Country> findByIsoCode(String isoCode);

}
