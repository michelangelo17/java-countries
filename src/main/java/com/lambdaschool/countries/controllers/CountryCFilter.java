package com.lambdaschool.countries.controllers;

import com.lambdaschool.countries.models.Country;

public interface CountryCFilter {
    boolean filter(Country c);
}
