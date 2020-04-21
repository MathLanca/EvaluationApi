package com.mackenzie.cif.evaluation.domain.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer {
    private String questionId;
    private String infoSource;

    private String problemDescription;

    private Integer generalGrade;

    private Integer cGrade;
    private Integer pGrade;

    private Integer locationGrade;
    private Integer extensionGrade;
    private Integer natureGrade;
}
