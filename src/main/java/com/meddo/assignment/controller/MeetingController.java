package com.meddo.assignment.controller;

import com.meddo.assignment.model.dto.MeetingRequest;
import com.meddo.assignment.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetingController {

    @Autowired private MeetingService meetingService;

    @PostMapping(value = "/schedule-meeting", produces = "application/json")
    public ResponseEntity<?> scheduleMeeting(@RequestBody MeetingRequest meeting) {
        return new ResponseEntity<>(meetingService.scheduleMeeting(meeting), HttpStatus.CREATED);
    }

    @GetMapping(value = "/meetings/{email}", produces = "application/json")
    public ResponseEntity<?> getEmployeeMeetings(@PathVariable String email) {
        return new ResponseEntity<>(meetingService.getEmployeeMeetings(email), HttpStatus.CREATED);
    }
}
