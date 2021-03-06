package com.tracking.TrackingAPI.event.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Reprezentuje event wraz ze wszystkimi wymaganymi danymi.
 */
@Entity
@Table(name = "event")
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Action action;

    private String ip;
    private String link;

    @NotNull
    @Column(name = "event_time")
    private Date eventTime;

    public Event() {
    }

    public Event(Action action, String ip, String link, Date eventTime) {
        this.action = action;
        this.ip = ip;
        this.link = link;
        this.eventTime = eventTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", action=" + action +
                ", ip='" + ip + '\'' +
                ", link='" + link + '\'' +
                ", eventTime=" + eventTime +
                '}';
    }
}
