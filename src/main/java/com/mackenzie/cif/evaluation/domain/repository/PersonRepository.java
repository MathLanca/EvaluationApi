package com.mackenzie.cif.evaluation.domain.repository;

import com.mackenzie.cif.evaluation.domain.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
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
}
