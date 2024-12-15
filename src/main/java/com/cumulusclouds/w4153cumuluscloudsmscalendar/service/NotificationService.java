package com.cumulusclouds.w4153cumuluscloudsmscalendar.service;

import com.cumulusclouds.w4153cumuluscloudsmscalendar.event.Notification;
import com.cumulusclouds.w4153cumuluscloudsmscalendar.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cumulusclouds.w4153cumuluscloudsmscalendar.event.Event;

import java.util.List;
import java.util.UUID;


@Service
public class NotificationService {

    public void notifyBooker(Event event) {
        // Logic to send a notification to the booker about the event's status
        System.out.println("Notification sent to booker: " + event.getBookerId());
    }

    public void notifyMusician(Event event) {
        // Logic to send a notification to the musician about the event match
        System.out.println("Notification sent to musician: " + event.getMusicianId());
    }

    public void notifyMatch(Event event) {
        notifyBooker(event);
        notifyMusician(event);
    }
}
