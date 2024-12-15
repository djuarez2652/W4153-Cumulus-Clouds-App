package com.cumulusclouds.w4153cumuluscloudsmscalendar.repository;

import com.cumulusclouds.w4153cumuluscloudsmscalendar.event.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Find notifications by recipient ID
    List<Notification> findByRecipientId(UUID recipientId);

    // Find notifications by event ID
    List<Notification> findByEventId(Long eventId);

    // Find notifications by recipient ID and event ID
    List<Notification> findByRecipientIdAndEventId(UUID recipientId, Long eventId);
}