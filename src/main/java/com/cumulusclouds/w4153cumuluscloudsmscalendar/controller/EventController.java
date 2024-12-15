package com.cumulusclouds.w4153cumuluscloudsmscalendar.controller;

import com.cumulusclouds.w4153cumuluscloudsmscalendar.event.Event;
import com.cumulusclouds.w4153cumuluscloudsmscalendar.event.EventStatus;
import com.cumulusclouds.w4153cumuluscloudsmscalendar.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Operation(summary = "Retrieve all events")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of events")
    @GetMapping("/")
    public ResponseEntity<List<EntityModel<Event>>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        List<EntityModel<Event>> resources = events.stream()
                .map(this::toEntityModel)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Retrieve an event by ID")
    @ApiResponse(responseCode = "200", description = "Event found successfully")
    @ApiResponse(responseCode = "404", description = "Event not found")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Event>> getEventById(@PathVariable Long id) {
        Optional<Event> eventOptional = eventService.getEventById(id);
        return eventOptional
                .map(this::toEntityModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new event")
    @ApiResponse(responseCode = "201", description = "Event created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid event data provided")
    @PostMapping("/")
    public ResponseEntity<EntityModel<Event>> createEvent(@RequestBody Event event) {
        Event savedEvent = eventService.createEvent(event);
        return ResponseEntity.ok(toEntityModel(savedEvent));
    }

    @Operation(summary = "Update an existing event")
    @ApiResponse(responseCode = "200", description = "Event updated successfully")
    @ApiResponse(responseCode = "404", description = "Event not found")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Event>> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        return eventService.updateEvent(id, eventDetails)
                .map(this::toEntityModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an event")
    @ApiResponse(responseCode = "204", description = "Event deleted successfully")
    @ApiResponse(responseCode = "404", description = "Event not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id) 
                ? ResponseEntity.noContent().build() 
                : ResponseEntity.notFound().build();
    }

    private EntityModel<Event> toEntityModel(Event event) {
        return EntityModel.of(event,
                linkTo(methodOn(EventController.class).getEventById(event.getId())).withSelfRel(),
                linkTo(methodOn(EventController.class).getAllEvents()).withRel("events"));
    }
}
