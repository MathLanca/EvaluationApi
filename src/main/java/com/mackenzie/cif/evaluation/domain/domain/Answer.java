package com.mackenzie.cif.evaluation.domain.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Answer {
    private String questionId;
    private String questionCode;
    private String questionDescription;
    private String infoSource;
    private String problemDescription;
    private BigDecimal generalGrade;
    private BigDecimal capacityGrade;
    private BigDecimal performanceGrade;
    private BigDecimal locationGrade;
    private BigDecimal extensionGrade;
    private BigDecimal natureGrade;
}
