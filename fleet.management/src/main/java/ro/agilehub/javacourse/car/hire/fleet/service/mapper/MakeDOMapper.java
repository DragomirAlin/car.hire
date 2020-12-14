package ro.agilehub.javacourse.car.hire.fleet.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.agilehub.javacourse.car.hire.fleet.service.domain.MakeDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Make;

@Mapper(componentModel = "spring", uses = ObjectIdFleetMapper.class)
public interface MakeDOMapper {

    @Mapping(target = "id", source = "make._id")
    MakeDO toMakeDO(Make make);
    Make toMake(MakeDO makeDO);
}
