package ro.agilehub.javacourse.car.hire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.entity.Country;
import ro.agilehub.javacourse.car.hire.repository.CountryRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/test/")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;


    @GetMapping("/all")
    public List<Country> viewAll(){
        return countryRepository.findAll();
    }
}
