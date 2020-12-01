package ro.agilehub.javacourse.car.hire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.RentalDTO;
import ro.agilehub.javacourse.car.hire.domain.UserDO;
import ro.agilehub.javacourse.car.hire.entity.User;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.mapper.CarDOMapper;
import ro.agilehub.javacourse.car.hire.domain.RentalDO;
import ro.agilehub.javacourse.car.hire.entity.Rental;

@Mapper(componentModel = "spring", uses = {ObjectIdRentalMapper.class, UserDOMapper.class, CarDOMapper.class})
public interface RentalDOMapper {

    @Mapping(target = "id", source = "rental._id")
    @Mapping(target = "startDate", source = "rental.startDate")
    @Mapping(target = "endDate", source = "rental.endDate")
    @Mapping(target = "status", source = "rental.status")
    @Mapping(target = "userDO", source = "userDO")
    @Mapping(target = "carDO", source = "carDO")
    RentalDO toRentalDO(Rental rental, CarDO carDO, UserDO userDO);

    @Mapping(target = "_id", source = "rentalDO.id")
    @Mapping(target = "startDate", source = "rentalDO.startDate")
    @Mapping(target = "endDate", source = "rentalDO.endDate")
    @Mapping(target = "status", source = "rentalDO.status")
    @Mapping(target = "user_id", source = "rentalDO.userDO.id")
    @Mapping(target = "car_id", source = "rentalDO.carDO.id")
    Rental toRental(RentalDO rentalDO);

}
