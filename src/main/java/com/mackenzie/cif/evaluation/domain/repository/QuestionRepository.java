package com.mackenzie.cif.evaluation.domain.repository;

import com.mackenzie.cif.evaluation.domain.domain.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}
