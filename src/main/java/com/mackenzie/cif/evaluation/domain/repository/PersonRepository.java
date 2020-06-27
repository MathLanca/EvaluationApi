package com.mackenzie.cif.evaluation.domain.repository;

import com.mackenzie.cif.evaluation.domain.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    List<Person> findAllByPatientNotNullAndPatientTherapistId(String id);
}
