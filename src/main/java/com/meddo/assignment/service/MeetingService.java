package com.meddo.assignment.service;

import com.meddo.assignment.model.dto.MeetingRequest;
import com.meddo.assignment.model.dto.MeetingResponse;
import com.meddo.assignment.model.entity.Calendar;
import com.meddo.assignment.model.entity.Candidate;
import com.meddo.assignment.model.entity.Event;
import com.meddo.assignment.repository.CalendarRepository;
import com.meddo.assignment.repository.CandidateRepository;
import com.meddo.assignment.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MeetingService {

    private EventRepository eventRepository;
    private CalendarRepository calendarRepository;
    private CandidateRepository candidateRepository;

    public MeetingResponse scheduleMeeting(MeetingRequest meeting) {
        List<Calendar> calendars = meeting.getReceiverEmail().stream().map(email -> {
            Candidate candidate = candidateRepository.findByEmail(email);
            return getCalendar(meeting, candidate);
        }).collect(Collectors.toList());

        Candidate senderCandidate = candidateRepository.findByEmail(meeting.getSenderEmail());
        calendars.add(getCalendar(meeting, senderCandidate));

        Event event = Event.builder()
                .startTime(meeting.getStartTime())
                .endTime(meeting.getEndTime())
                .description(meeting.getDescription())
                .calendars(calendars)
                .build();

        Event organizedEvent = eventRepository.save(event);
        return getMeetingResponse(calendars, organizedEvent);
    }

    public List<MeetingResponse> getEmployeeMeetings(String email) {
        Candidate candidate = candidateRepository.findByEmail(email);
        List<UUID> eventIds = calendarRepository.findEventIdsByCandidateId(candidate.getId());
        return eventIds.stream().map(eventId -> {
            Event event = eventRepository.findById(eventId).get();
            List<Long> candidateIds = calendarRepository.findByEventId(eventId.toString());
            List<Candidate> candidates = (List<Candidate>) candidateRepository.findAllById(candidateIds);
            return MeetingResponse.builder()
                    .eventId(event.getId().toString())
                    .startTime(event.getStartTime())
                    .endTime(event.getEndTime())
                    .description(event.getDescription())
                    .candidates(candidates)
                    .build();
        }).collect(Collectors.toList());
    }

    private static MeetingResponse getMeetingResponse(List<Calendar> calendars, Event organizedEvent) {
        return MeetingResponse.builder()
                .eventId(organizedEvent.getId().toString())
                .startTime(organizedEvent.getStartTime())
                .description(organizedEvent.getDescription())
                .endTime(organizedEvent.getEndTime())
                .candidates(calendars.stream()
                        .map(Calendar::getCandidate)
                        .collect(Collectors.toList()))
                .build();
    }

    private static Calendar getCalendar(MeetingRequest meeting, Candidate candidate) {
        return Calendar.builder()
                .startTime(meeting.getStartTime())
                .endTime(meeting.getEndTime()).candidate(candidate).build();
    }
}
