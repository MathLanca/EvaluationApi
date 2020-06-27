package com.mackenzie.cif.evaluation.domain.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "Person")
public class Person {
    @Id
    private String id;

    private String cpf;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDateTime birthDate;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    private String sex;

    private String telephoneNumber;

    private Address address;

    private Patient patient;

    private Boolean active;

    public String getFullName(){
        return this.getFirstName() + " " + this.getLastName();
    }
}
