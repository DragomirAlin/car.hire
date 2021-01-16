package ro.agilehub.javacourse.car.hire.rental.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.RentalDTO;
import ro.agilehub.javacourse.car.hire.api.model.RentalResponseDTO;
import ro.agilehub.javacourse.car.hire.rental.service.domain.RentalDO;
import ro.agilehub.javacourse.car.hire.rental.service.mapper.ObjectIdRentalMapper;
import ro.agilehub.javacourse.car.hire.user.controller.mapper.UserDTOMapper;
import ro.agilehub.javacourse.car.hire.user.service.domain.UserDO;
import ro.agilehub.javacourse.car.hire.fleet.service.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.service.mapper.CarDOMapper;
import ro.agilehub.javacourse.car.hire.fleet.controller.mapper.CarDTOMapper;
import ro.agilehub.javacourse.car.hire.fleet.service.mapper.MakeDOMapper;
import ro.agilehub.javacourse.car.hire.user.service.mapper.CountryDOMapper;
import ro.agilehub.javacourse.car.hire.user.service.mapper.UserDOMapper;

@Mapper(componentModel = "spring", uses = {ObjectIdRentalMapper.class, UserDOMapper.class,
        CarDOMapper.class, CountryDOMapper.class, MakeDOMapper.class, UserDTOMapper.class, CarDTOMapper.class})
public interface RentalDTOMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", source = "rentalDTO.startDate")
    @Mapping(target = "endDate", source = "rentalDTO.endDate")
    @Mapping(target = "status", source = "rentalDTO.status")
    @Mapping(target = "userDO", source = "user")
    @Mapping(target = "carDO", source = "car")
    RentalDO toRentalDO(RentalDTO rentalDTO, CarDO car, UserDO user);

    @Mapping(target = "id", source = "rentalDO.id")
    @Mapping(target = "startDate", source = "rentalDO.startDate")
    @Mapping(target = "endDate", source = "rentalDO.endDate")
    @Mapping(target = "status", source = "rentalDO.status")
    @Mapping(target = "user", source = "rentalDO.userDO")
    @Mapping(target = "car", source = "rentalDO.carDO")
    RentalResponseDTO toRentalResponseDTO(RentalDO rentalDO);
}