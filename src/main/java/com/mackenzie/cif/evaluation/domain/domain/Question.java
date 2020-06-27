package com.mackenzie.cif.evaluation.domain.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "Question")
public class Question {
    @Id
    private String id;
    @NotNull
    private String code;
    @NotNull
    private String title;
    @NotNull
    private String group;

    private String description;
    private String inclusion;
    private String exclusion;
}
