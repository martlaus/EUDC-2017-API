package eudcApi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eudcApi.rest.jackson.map.DateTimeDeserializer;
import eudcApi.rest.jackson.map.DateTimeSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by mart on 11.10.16.
 */
@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;
    
    @Column
    private String location;
    
    @Column
    private String color;

    @ManyToOne
    @JoinColumn(nullable = null, name = "eventType")
    private EventType eventType;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime startTime;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getStartTime() {
        return startTime;
    }

    @JsonDeserialize(using = DateTimeDeserializer.class)
    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getEndTime() {
        return endTime;
    }

    @JsonDeserialize(using = DateTimeDeserializer.class)
    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }
}
