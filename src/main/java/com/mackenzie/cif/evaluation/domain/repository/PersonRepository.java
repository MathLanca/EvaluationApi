package com.mackenzie.cif.evaluation.domain.repository;

import com.mackenzie.cif.evaluation.domain.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class PersonRepository {

    public Person getPersonDetails(String personId){
        log.info("Get person details started >>>>>");
        RestTemplate restTemplate = new RestTemplate();

        final String url = "https://java-cif-person-api.herokuapp.com/v1/person/findById/{id}";

        Map<String,String> params = new HashMap<String, String>();
        params.put("id", personId);

        log.info("Requesting details from id {} >>>>>", personId);
        ResponseEntity<Person> response = restTemplate.getForEntity(url, Person.class,params);
        if(response.hasBody()){
            log.info("Response OK! {}", response);
            return response.getBody();
        }
        log.error("Response with errors!");
        return null;
    }

    public List<Person> getPaientList(String therapistId){
        log.info("List patients >>>>");
        RestTemplate restTemplate = new RestTemplate();

        final String url = "https://java-cif-person-api.herokuapp.com/v1/person/findPatientsByTherapist/{id}";

        Map<String,String> params = new HashMap<String, String>();
        params.put("id", therapistId);

        log.info("Requesting details from id {} >>>>>", therapistId);
        ResponseEntity<List<Person>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>(){},
                params
        );
        if(response.hasBody()){
            log.info("Response OK! {}", response);
            return response.getBody();
        }
        log.error("Response with errors!");
        return null;
    }
}
