package ro.agilehub.javacourse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.entity.User;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.mapper.CarDOMapper;
import ro.agilehub.javacourse.car.hire.fleet.mapper.CarDOMapperImpl;
import ro.agilehub.javacourse.car.hire.mapper.UserDOMapper;
import ro.agilehub.javacourse.domain.RentalDO;
import ro.agilehub.javacourse.entity.Rental;

@Mapper(componentModel = "spring", uses = {ObjectIdRentalMapper.class, UserDOMapper.class, CarDOMapper.class})
public interface RentalDOMapper {

    @Mapping(target = "id", source = "rental._id")
    @Mapping(target = "startDate", source = "rental.startDate")
    @Mapping(target = "endDate", source = "rental.endDate")
    @Mapping(target = "status", source = "rental.status")
    @Mapping(target = "userDO", source = "user")
    @Mapping(target = "carDO", source = "car")
    RentalDO toRentalDO(Rental rental, Car car, User user);

    @Mapping(target = "_id", source = "rentalDO.id")
    @Mapping(target = "startDate", source = "rentalDO.startDate")
    @Mapping(target = "endDate", source = "rentalDO.endDate")
    @Mapping(target = "status", source = "rentalDO.status")
    @Mapping(target = "user_id", source = "rentalDO.userDO.id")
    @Mapping(target = "car_id", source = "rentalDO.carDO.id")
    Rental toRental(RentalDO rentalDO);

}
