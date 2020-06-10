package com.mackenzie.cif.evaluation.domain.serice;

import com.mackenzie.cif.evaluation.domain.domain.Evaluation;
import com.mackenzie.cif.evaluation.domain.domain.Person;
import com.mackenzie.cif.evaluation.domain.repository.EvaluationRepository;
import com.mackenzie.cif.evaluation.domain.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EvaluationService {

    @Autowired
    EvaluationRepository repository;

    PersonRepository personRepository = new PersonRepository();

    public void newEvaluation(Evaluation evaluation){
        try{
            repository.save(evaluation);
        }catch (Exception e){
            log.error("Could not save this evaluation");
            throw e;
        }
    }

    public List<Evaluation> retrieveTherapistEvaluations(String therapistId){
        List<Evaluation> evaluations;
        try{
            evaluations = repository.findAllByTherapistId(therapistId);

            log.info("getting patient list");
            List<Person> patients;
            try{
                 patients = personRepository.getPaientList(therapistId);
                 if(patients == null){
                     log.error("Could not get patient list");
                     throw new RuntimeException("Could not get patient list");
                 }
            }catch (Exception e){
                log.error("Could not get patient list");
                throw e;
            }
            if(patients.isEmpty()) {
                log.error("No patients found");
                throw new RuntimeException("No patients found");
            }

            try{
                this.setPatientsNameInEvaluation(patients,evaluations);
            }catch (Exception e){
                log.error("Could not set patient name into evaluations");
                throw e;
            }

            evaluations.sort((e1, e2) -> {
                if (e1.getDate() == null || e2.getDate() == null) return 0;
                return e1.getDate().compareTo(e2.getDate());
            });
        }catch (Exception e){
            log.error("Could not save this evaluation");
            throw e;
        }
        return evaluations;
    }

    private void setPatientsNameInEvaluation(List<Person> patients, List<Evaluation> evaluations){
        log.info("Setting patient names in evaluations");
        evaluations.forEach(evaluation ->
            evaluation.setPatientName(findPatientName(evaluation.getPatientId(),patients))
        );
    }

    private String findPatientName(String patientId,List<Person> patients){
        log.info("Searching patient name");
        Person person = patients.stream().filter(patient ->
            patient.getId().equals(patientId)
        ).findAny().orElse(null);
        return person != null ? person.getFullName() : null;
    }

    public Evaluation retrieveEvaluation(String evaluationID){
        Evaluation evaluation = null;
        try {
            Optional<Evaluation> response = repository.findById(evaluationID);
            if(response.isPresent()){
                evaluation = response.get();

                Person therapist = personRepository.getPersonDetails(response.get().getTherapistId());
                if(therapist != null){
                    evaluation.setTherapistName(therapist.getFirstName().concat(" "+therapist.getLastName()));
                }
                Person patient = personRepository.getPersonDetails(response.get().getPatientId());
                if(patient != null){
                    evaluation.setPatientName(patient.getFirstName().concat(" "+patient.getLastName()));
                }
            }
        }catch (Exception e){
            throw e;
        }
        return evaluation;
    }
}
