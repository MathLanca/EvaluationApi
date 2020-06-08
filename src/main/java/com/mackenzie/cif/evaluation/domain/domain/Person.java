package com.mackenzie.cif.evaluation.domain.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Person {
    private String id;
    private String cpf;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    private String email;
    private String password;
    private String sex;
    private String telephoneNumber;
    private Address address;
    private Boolean active;
}
