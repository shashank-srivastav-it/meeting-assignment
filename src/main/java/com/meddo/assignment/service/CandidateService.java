package com.meddo.assignment.service;

import com.meddo.assignment.model.entity.Calendar;
import com.meddo.assignment.model.entity.Candidate;
import com.meddo.assignment.repository.CalendarRepository;
import com.meddo.assignment.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    @Autowired private CandidateRepository candidateRepository;

    public Candidate createCandidate(Candidate candidate){
        return candidateRepository.save(candidate);
    }
}
