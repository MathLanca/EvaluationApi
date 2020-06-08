package com.mackenzie.cif.evaluation.domain.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Address {
    private String publicPlace;
    private Integer houseNumber;
    private String neighborhood;
    private String city;
    private String state;
    private String postalCode;
}
