package ro.agilehub.javacourse.car.hire.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.user.service.domain.CountryDO;
import ro.agilehub.javacourse.car.hire.user.service.mapper.CountryDOMapper;
import ro.agilehub.javacourse.car.hire.user.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.user.service.CountryService;

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
