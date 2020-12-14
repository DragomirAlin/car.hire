package ro.agilehub.javacourse.car.hire.fleet.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.fleet.service.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.entity.Make;


@Mapper(componentModel = "spring", uses = {ObjectIdFleetMapper.class, MakeDOMapper.class})
public interface CarDOMapper {

    @Mapping(target = "id", source = "car._id")
    @Mapping(target = "makeDO", source = "make")
    @Mapping(target = "model", source = "car.model")
    @Mapping(target = "year", source = "car.year")
    @Mapping(target = "mileage", source = "car.mileage")
    @Mapping(target = "fuel", source = "car.fuel")
    @Mapping(target = "carClazz", source = "car.carClazz")
    @Mapping(target = "status", source = "car.status")
    CarDO toCarDO(Car car, Make make);

    @Mapping(target = "_id", source = "carDO.id")
    @Mapping(target = "make_id", source = "carDO.makeDO.id")
    @Mapping(target = "model", source = "carDO.model")
    @Mapping(target = "year", source = "carDO.year")
    @Mapping(target = "mileage", source = "carDO.mileage")
    @Mapping(target = "fuel", source = "carDO.fuel")
    @Mapping(target = "carClazz", source = "carDO.carClazz")
    @Mapping(target = "status", source = "carDO.status")
    Car toCar(CarDO carDO);


}
