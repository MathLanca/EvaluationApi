package com.mackenzie.cif.evaluation.application;

import com.mackenzie.cif.evaluation.domain.domain.Evaluation;
import com.mackenzie.cif.evaluation.domain.serice.EvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/evaluation")
@Slf4j
public class EvaluationController {

    @Autowired
    private EvaluationService service;
    
    @CrossOrigin("*")
    @PostMapping("/new")
    public ResponseEntity<?> newEvaluation(@RequestBody @Valid Evaluation body){
        log.info("New evaluation started >>>>>");

        try{
            log.info("calling service class >>>>>");
            service.newEvaluation(body);
        }catch (Exception e){
            log.error("Error while saving a new evaluation");
            return new ResponseEntity<>("Error while saving a new evaluation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("evaluation created >>>>>");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin("*")
    @GetMapping("/therapistevaluations/{id}")
    public ResponseEntity<?> retrieveTherapistEvaluations(@PathVariable String id){
        log.info("list evaluations >>>>>");
        List<Evaluation> evaluations;
        try{
            evaluations = service.retrieveTherapistEvaluations(id);
        }catch (Exception e ){
            return new ResponseEntity<>("Error while retrieving evaluations", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }
}
