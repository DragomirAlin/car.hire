package ro.agilehub.javacourse.car.hire.fleet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.CarResponseDTO;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.domain.MakeDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Make;


@Mapper(componentModel = "spring", uses = {ObjectIdFleetMapper.class, MakeDOMapper.class})
public interface CarDTOMapper {

    @Mapping(target = "id", source = "carDO.id")
    @Mapping(target = "make", source = "carDO.makeDO.makeName")
    @Mapping(target = "model", source = "carDO.model")
    @Mapping(target = "year", source = "carDO.year")
    @Mapping(target = "mileage", source = "carDO.mileage")
    @Mapping(target = "fuel", source = "carDO.fuel")
    @Mapping(target = "carClazz", source = "carDO.carClazz")
    @Mapping(target = "status", source = "carDO.status")
    CarResponseDTO toCarResponseDTO(CarDO carDO);

    @Mapping(target = "makeDO", source = "makeDO")
    @Mapping(target = "model", source = "carDTO.model")
    @Mapping(target = "year", source = "carDTO.year")
    @Mapping(target = "mileage", source = "carDTO.mileage")
    @Mapping(target = "fuel", source = "carDTO.fuel")
    @Mapping(target = "carClazz", source = "carDTO.carClazz")
    @Mapping(target = "status", source = "carDTO.status")
    CarDO toCarDO(CarDTO carDTO, MakeDO makeDO);

}
