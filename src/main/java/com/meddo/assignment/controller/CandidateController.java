package com.meddo.assignment.controller;

import com.meddo.assignment.model.dto.Meeting;
import com.meddo.assignment.model.entity.Candidate;
import com.meddo.assignment.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping(value = "/create-user", produces = "application/json")
    public ResponseEntity<?> createUser(@RequestBody Candidate candidate) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
