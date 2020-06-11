package com.mackenzie.cif.evaluation.domain.serice;

import com.mackenzie.cif.evaluation.domain.domain.Evaluation;
import com.mackenzie.cif.evaluation.domain.domain.Person;
import com.mackenzie.cif.evaluation.domain.domain.Question;
import com.mackenzie.cif.evaluation.domain.repository.EvaluationRepository;
import com.mackenzie.cif.evaluation.domain.repository.PersonRepository;
import com.mackenzie.cif.evaluation.domain.repository.QuestionRepository;
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

    @Autowired
    QuestionRepository questionRepository;

    PersonRepository personRepository = new PersonRepository();

    public void newEvaluation(Evaluation evaluation) {
        try {
            repository.save(evaluation);
        } catch (Exception e) {
            log.error("Could not save this evaluation");
            throw e;
        }
    }

    public List<Evaluation> retrieveTherapistEvaluations(String therapistId) {
        List<Evaluation> evaluations;
        try {
            evaluations = repository.findAllByTherapistId(therapistId);

            log.info("getting patient list");
            List<Person> patients;
            try {
                patients = personRepository.getPaientList(therapistId);
                if (patients == null) {
                    log.error("Could not get patient list");
                    throw new RuntimeException("Could not get patient list");
                }
            } catch (Exception e) {
                log.error("Could not get patient list");
                throw e;
            }

            if (patients.isEmpty()) {
                log.error("No patients found");
                throw new RuntimeException("No patients found");
            }

            try {
                this.setNamesInEvaluation(patients, evaluations);
            } catch (Exception e) {
                log.error("Could not set patient name into evaluations");
                throw e;
            }

            evaluations.sort((e1, e2) -> {
                if (e1.getDate() == null || e2.getDate() == null) return 0;
                return e1.getDate().compareTo(e2.getDate());
            });
        } catch (Exception e) {
            log.error("Could not save this evaluation");
            throw e;
        }
        return evaluations;
    }

    public Evaluation retrieveEvaluation(String evaluationID) {
        Evaluation evaluation = null;
        try {
            Optional<Evaluation> response = repository.findById(evaluationID);
            if (response.isPresent()) {
                evaluation = response.get();

                Person therapist = personRepository.getPersonDetails(response.get().getTherapistId());
                if (therapist != null) {
                    evaluation.setTherapistName(therapist.getFullName());
                }
                Person patient = personRepository.getPersonDetails(response.get().getPatientId());
                if (patient != null) {
                    evaluation.setPatientName(patient.getFullName());
                }
            }
        } catch (Exception e) {
            throw e;
        }

        try{
           List<Question> questoins = questionRepository.findAll();
           if(questoins == null || questoins.isEmpty()){
               log.error("Could not get questions");
               throw new RuntimeException("Could not get questions");
           }
           if(evaluation != null) {
               setQuestionInEvaluation(questoins, evaluation);
           }
        }catch (Exception e){
            log.error("Could not get questions");
            throw e;
        }
        return evaluation;
    }

    private void setQuestionInEvaluation(List<Question> questions, Evaluation evaluation){
        evaluation.getAnswers().forEach(answer -> {
            Question quest = findQuestion(answer.getQuestionId(),questions);
            answer.setQuestionCode(quest.getCode());
            answer.setQuestionDescription(quest.getDescription());
        });
    }

    private Question findQuestion(String questionId,List<Question> questions){
        return questions.stream()
                .filter(question -> question.getId().equals(questionId))
                .findAny()
                .orElse(null);
    }

    private void setNamesInEvaluation(List<Person> patients, List<Evaluation> evaluations) {
        Person therapist;
        try {
            therapist = personRepository.getPersonDetails(evaluations.get(0).getTherapistId());
        } catch (Exception e) {
            log.error("Could not get therapist data");
            throw e;
        }

        log.info("Setting patient names in evaluations");
        evaluations.forEach(evaluation -> {
                    evaluation.setTherapistName(therapist.getFullName());
                    evaluation.setPatientName(findPatientName(evaluation.getPatientId(), patients));
                }
        );
    }

    private String findPatientName(String patientId, List<Person> patients) {
        log.info("Searching patient name");
        Person person = patients.stream().filter(patient ->
                patient.getId().equals(patientId)
        ).findAny().orElse(null);
        return person != null ? person.getFullName() : null;
    }
}
