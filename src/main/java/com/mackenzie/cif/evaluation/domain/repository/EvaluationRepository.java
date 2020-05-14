package com.mackenzie.cif.evaluation.domain.repository;

import com.mackenzie.cif.evaluation.domain.domain.Evaluation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends MongoRepository<Evaluation, String> {
    List<Evaluation> findAllByTherapistId(String therapistId);
}
