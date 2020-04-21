package com.mackenzie.cif.evaluation.domain.serice;

import com.mackenzie.cif.evaluation.domain.domain.Evaluation;
import com.mackenzie.cif.evaluation.domain.repository.EvaluationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class EvaluationService {

    @Autowired
    EvaluationRepository repository;

    public void newEvaluation(Evaluation evaluation){
        try{
            repository.save(evaluation);
        }catch (Exception e){
            log.error("Could not save this evaluation");
            throw e;
        }
    }

    public List<Evaluation> retrieveTherapistEvaluations(String therapistCpf){
        List<Evaluation> evaluations;
        try{
            evaluations = repository.findAllByTherapistCpf(therapistCpf);
            if(evaluations != null) {
                evaluations.sort((e1, e2) -> {
                    if (e1.getDate() == null || e2.getDate() == null) return 0;
                    return e1.getDate().compareTo(e2.getDate());
                });
            }
        }catch (Exception e){
            log.error("Could not save this evaluation");
            throw e;
        }
        return evaluations;
    }
}
