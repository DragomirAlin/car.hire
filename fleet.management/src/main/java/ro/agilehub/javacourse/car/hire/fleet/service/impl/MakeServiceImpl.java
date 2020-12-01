package ro.agilehub.javacourse.car.hire.fleet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.fleet.domain.MakeDO;
import ro.agilehub.javacourse.car.hire.fleet.mapper.MakeDOMapper;
import ro.agilehub.javacourse.car.hire.fleet.repository.MakeRepository;
import ro.agilehub.javacourse.car.hire.fleet.service.MakeService;

@Service
public class MakeServiceImpl implements MakeService {

    @Autowired
    private MakeRepository makeRepository;
    @Autowired
    private MakeDOMapper mapper;

    @Override
    public MakeDO findByMakeName(String makeName) {
        var make = makeRepository
                .findByMakeName(makeName)
                .orElse(null);
        return mapper.toMakeDO(make);
    }
}
