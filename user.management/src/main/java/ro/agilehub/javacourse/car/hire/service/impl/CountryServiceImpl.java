package ro.agilehub.javacourse.car.hire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.service.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.service.mapper.CountryDOMapper;
import ro.agilehub.javacourse.car.hire.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.service.CountryService;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryDOMapper mapper;

    public CountryDO findByIsoCode(String isoCode){
        var country = countryRepository
                .findByIsoCode(isoCode)
                .orElse(null);

        return mapper.toCountryDO(country);
    }}
