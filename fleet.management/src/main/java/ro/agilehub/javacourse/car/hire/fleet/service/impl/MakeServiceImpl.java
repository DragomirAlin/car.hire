package ro.agilehub.javacourse.car.hire.fleet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.fleet.service.domain.MakeDO;
import ro.agilehub.javacourse.car.hire.fleet.service.mapper.MakeDOMapper;
import ro.agilehub.javacourse.car.hire.fleet.repository.MakeRepository;
import ro.agilehub.javacourse.car.hire.fleet.service.MakeService;

@Service
@RequiredArgsConstructor
public class MakeServiceImpl implements MakeService {

    private final MakeRepository makeRepository;
    private final MakeDOMapper mapper;

    @Override
    public MakeDO findByMakeName(String makeName) {
        var make = makeRepository
                .findByMakeName(makeName)
                .orElse(null);
        return mapper.toMakeDO(make);
    }
}
