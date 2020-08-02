package com.lambdaschool.countries.repositories;

import com.lambdaschool.countries.models.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryR extends CrudRepository<Country, Long> {
}
