package com.cumulusclouds.w4153cumuluscloudsmscalendar.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "notification_id", updatable = false, nullable = false)
    private UUID notificationId;

    @Column(name = "notification_from_id", nullable = false)
    private UUID fromId;

    @Column(name = "notification_to_id", nullable = false)
    private UUID toId;

    @Column(name = "notification_msg")
    private String msg;

    @Column(name = "notification_time")
    private String time;

    public UUID getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(UUID notificationId) {
        this.notificationId = notificationId;
    }

    public UUID getFromId() {
        return fromId;
    }

    public void setFromId(UUID user1Id) {
        this.fromId = user1Id;
    }

    public UUID getToId() {
        return toId;
    }

    public void setToId(UUID user2Id) {
        this.toId = user2Id;
    }

    public String getNotificationMsg() {
        return msg;
    }

    public void setNotificationMsg(String newMsg) {
        this.msg = newMsg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String newTime) {
        this.time = newTime;
    }
}