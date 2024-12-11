package com.cumulusclouds.w4153cumuluscloudsmscalendar.repository;

import com.cumulusclouds.w4153cumuluscloudsmscalendar.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
  List<Notification> findByNotificationId(UUID notificationId);
}