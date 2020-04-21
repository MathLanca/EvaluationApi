package com.mackenzie.cif.evaluation.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping()
    public String get(){
        return "Evaluation API";
    }
}

