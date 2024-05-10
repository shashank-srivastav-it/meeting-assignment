package com.meddo.assignment.repository;

import com.meddo.assignment.model.entity.Calendar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CalendarRepository extends CrudRepository<Calendar, UUID> {
    @Query("SELECT c.id FROM Calendar c WHERE c.candidate.id = :candidateId")
    List<UUID> findCalendarIdsByCandidateId(Long candidateId);

    @Query(value = "SELECT event_id FROM calendar WHERE candidate_id = :candidateId", nativeQuery = true)
    List<UUID> findEventIdsByCandidateId(Long candidateId);

    @Query(value = "SELECT candidate_id FROM calendar WHERE event_id = :eventId", nativeQuery = true)
    List<Long> findByEventId(String eventId);
}
