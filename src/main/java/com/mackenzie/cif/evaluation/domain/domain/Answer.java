package com.mackenzie.cif.evaluation.domain.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer {
    private String questionId;
    private String infoSource;
    private String problemDescription;
    private BigDecimal generalGrade;
    private BigDecimal cGrade;
    private BigDecimal pGrade;
    private BigDecimal locationGrade;
    private BigDecimal extensionGrade;
    private BigDecimal natureGrade;
}
