package com.mackenzie.cif.evaluation.domain.domain;


import lombok.Data;

@Data
public class Patient {
    private Person therapist;
    private String note;
}
