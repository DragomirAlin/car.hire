package ro.agilehub.javacourse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.RentalDTO;
import ro.agilehub.javacourse.car.hire.api.model.RentalResponseDTO;
import ro.agilehub.javacourse.car.hire.entity.User;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.mapper.CarDOMapper;
import ro.agilehub.javacourse.car.hire.fleet.mapper.CarDTOMapper;
import ro.agilehub.javacourse.car.hire.mapper.UserDOMapper;
import ro.agilehub.javacourse.car.hire.mapper.UserDTOMapper;
import ro.agilehub.javacourse.domain.RentalDO;

@Mapper(componentModel = "spring", uses = {ObjectIdRentalMapper.class, UserDOMapper.class,
        CarDOMapper.class, UserDTOMapper.class, CarDTOMapper.class})
public interface RentalDTOMapper {

    @Mapping(target = "id", source = "rentalDO.id")
    @Mapping(target = "startDate", source = "rentalDO.startDate")
    @Mapping(target = "endDate", source = "rentalDO.endDate")
    @Mapping(target = "status", source = "rentalDO.status")
    @Mapping(target = "user", source = "rentalDO.userDO")
    @Mapping(target = "car", source = "rentalDO.carDO")
    RentalResponseDTO toRentalResponseDTO(RentalDO rentalDO);

    @Mapping(target = "startDate", source = "rentalDTO.startDate")
    @Mapping(target = "endDate", source = "rentalDTO.endDate")
    @Mapping(target = "status", source = "rentalDTO.status")
    @Mapping(target = "userDO", source = "user")
    @Mapping(target = "carDO", source = "car")
    RentalDO toRentalDO(RentalDTO rentalDTO, Car car, User user);
}
