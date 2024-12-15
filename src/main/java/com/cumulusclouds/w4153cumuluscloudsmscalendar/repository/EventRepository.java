package com.cumulusclouds.w4153cumuluscloudsmscalendar.repository;

import com.cumulusclouds.w4153cumuluscloudsmscalendar.event.Event;
import com.cumulusclouds.w4153cumuluscloudsmscalendar.event.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Find events by booker ID
    List<Event> findByBookerId(UUID bookerId);

    // Find events by musician ID
    List<Event> findByMusicianId(UUID musicianId);

    // Find events by status
    List<Event> findByStatus(EventStatus status);

    // Find events by booker ID and status
    List<Event> findByBookerIdAndStatus(UUID bookerId, EventStatus status);
}
