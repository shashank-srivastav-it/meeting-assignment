package com.meddo.assignment.controller;

import com.meddo.assignment.model.dto.Meeting;
import com.meddo.assignment.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping(value = "/schedule-meeting", produces = "application/json")
    public ResponseEntity<?> createMeeting(@RequestBody Meeting meeting) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
