package com.mackenzie.cif.evaluation.domain.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "Evaluation")
public class Evaluation {

    @Id
    private String id;

    @NotNull
    private LocalDateTime date;
    @NotNull
    private String location;
    @NotNull
    private String therapistId;
    private String therapistName;
    @NotNull
    private String patientId;
    private String patientName;
    @NotNull
    private List<Answer> answers;
}
