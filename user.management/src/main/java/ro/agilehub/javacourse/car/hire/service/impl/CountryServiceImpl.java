package ro.agilehub.javacourse.car.hire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.entity.Country;
import ro.agilehub.javacourse.car.hire.mapper.CountryDOMapper;
import ro.agilehub.javacourse.car.hire.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountryDOMapper mapper;

    public CountryDO findByIsoCode(String isoCode){
        var country = countryRepository
                .findByIsoCode(isoCode)
                .orElse(null);

        return mapper.toCountryDO(country);
    }}
