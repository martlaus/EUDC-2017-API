package eudcApi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eudcApi.rest.jackson.map.DateTimeDeserializer;
import eudcApi.rest.jackson.map.DateTimeSerializerV2;
import org.joda.time.DateTime;

/**
 * Created by mart on 11.10.16.
 */
public class EventV2 {
    private Long id;
    private String title;
    private String description;
    private String location;
    private EventType eventType;
    private DateTime startTime;
    private DateTime endTime;

    private EventV2(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setDescription(builder.description);
        setLocation(builder.location);
        setEventType(builder.eventType);
        setStartTime(builder.startTime);
        setEndTime(builder.endTime);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @JsonSerialize(using = DateTimeSerializerV2.class)
    public DateTime getStartTime() {
        return startTime;
    }

    @JsonDeserialize(using = DateTimeDeserializer.class)
    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    @JsonSerialize(using = DateTimeSerializerV2.class)
    public DateTime getEndTime() {
        return endTime;
    }

    @JsonDeserialize(using = DateTimeDeserializer.class)
    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }


    public static final class Builder {
        private Long id;
        private String title;
        private String description;
        private String location;
        private EventType eventType;
        private DateTime startTime;
        private DateTime endTime;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder startTime(DateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(DateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public EventV2 build() {
            return new EventV2(this);
        }
    }
}
