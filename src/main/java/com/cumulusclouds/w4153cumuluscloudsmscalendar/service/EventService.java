package com.cumulusclouds.w4153cumuluscloudsmscalendar.service;

import com.cumulusclouds.w4153cumuluscloudsmscalendar.event.Event;
import com.cumulusclouds.w4153cumuluscloudsmscalendar.event.EventStatus;
import com.cumulusclouds.w4153cumuluscloudsmscalendar.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Retrieve all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Retrieve an event by ID
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // Create a new event
    public Event createEvent(Event event) {
        event.setStatus(EventStatus.PENDING);
        return eventRepository.save(event);
    }

    // Update an existing event
    public Optional<Event> updateEvent(Long id, Event updatedEvent) {
        return eventRepository.findById(id)
                .map(event -> {
                    event.setName(updatedEvent.getName());
                    event.setDescription(updatedEvent.getDescription());
                    event.setDateTime(updatedEvent.getDateTime());
                    event.setLocation(updatedEvent.getLocation());
                    event.setStatus(updatedEvent.getStatus());
                    return eventRepository.save(event);
                });
    }

    // Delete an event
    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Match a musician to an event
    public Optional<Event> matchMusician(Long eventId, UUID musicianId) {
        return eventRepository.findById(eventId)
                .map(event -> {
                    event.setMusicianId(musicianId);
                    event.setStatus(EventStatus.MATCHED);
                    return eventRepository.save(event);
                });
    }

    // Retrieve events created by a specific booker
    public List<Event> getEventsByBookerId(UUID bookerId) {
        return eventRepository.findByBookerId(bookerId);
    }
}
