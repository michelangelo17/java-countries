package com.lambdaschool.countries.controllers;

import com.lambdaschool.countries.models.Country;
import com.lambdaschool.countries.repositories.CountryR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryC {
    @Autowired
    CountryR countryR;

    private List<Country> filter(List<Country> countryList, CountryCFilter f) {
        List<Country> filteredCountryList = new ArrayList<>();
        for (Country c : countryList) {
            if (f.filter(c)) {
                filteredCountryList.add(c);
            }
        }
        return filteredCountryList;
    }

    @GetMapping(value = "names/all", produces = {"application/json"})
    public ResponseEntity<?> listAllCountries() {
        List<Country> countries = new ArrayList<>();
        countryR.findAll().iterator().forEachRemaining(countries::add);
        countries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping(value = "start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> listCountriesBeginningWithLetter(@PathVariable char letter) {
        List<Country> countries = new ArrayList<>();
        countryR.findAll().iterator().forEachRemaining(countries::add);
        List<Country> filteredList = filter(countries, c -> Character.toLowerCase(c.getName().charAt(0)) == Character.toLowerCase(letter));
        filteredList.forEach(System.out::println);
        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }
}
