package eudcApi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eudcApi.rest.jackson.map.DateTimeDeserializer;
import eudcApi.rest.jackson.map.DateTimeSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;


/**
 * Created by karl on 12.03.16.
 */

@Entity
public class TimerCard {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime endDate;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime created;

    @ManyToMany(fetch = EAGER, cascade = {PERSIST, MERGE})
//  @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "TimerCard_User",
            joinColumns = {@JoinColumn(name = "timercard")},
            inverseJoinColumns = {@JoinColumn(name = "user")},
            uniqueConstraints = @UniqueConstraint(columnNames = {"timercard", "user"}))
    private List<User> users;

    @Column(nullable = true, unique = false)
    private String title;

    @Column(nullable = true)
    private String locationId;

    @Column(nullable = true)
    private String fullLocation;

    @Column(nullable = true)
    private String topic;

    @Column(nullable = true)
    private String team;

    public TimerCard(Builder builder) {
        setId(builder.id);
        setEndDate(builder.endDate);
        setCreated(builder.created);
        users = builder.users;
        setTitle(builder.title);
        setLocationId(builder.locationId);
        setFullLocation(builder.fullLocation);
        setTopic(builder.topic);
        setTeam(builder.team);
    }

    public TimerCard() {
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullLocation() {
        return fullLocation;
    }

    public void setFullLocation(String fullLocation) {
        this.fullLocation = fullLocation;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getCreated() {
        return created;
    }

    @JsonDeserialize(using = DateTimeDeserializer.class)
    public void setCreated(DateTime created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getEndDate() {
        return endDate;
    }

    @JsonDeserialize(using = DateTimeDeserializer.class)
    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public static final class Builder {
        private Long id;
        private DateTime endDate;
        private DateTime created;
        private List<User> users;
        private String title;
        private String locationId;
        private String fullLocation;
        private String topic;
        private String team;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder endDate(DateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder created(DateTime created) {
            this.created = created;
            return this;
        }

        public Builder users(List<User> users) {
            this.users = users;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder locationId(String locationId) {
            this.locationId = locationId;
            return this;
        }

        public Builder fullLocation(String fullLocation) {
            this.fullLocation = fullLocation;
            return this;
        }

        public Builder topic(String topic) {
            this.topic = topic;
            return this;
        }

        public Builder team(String team) {
            this.team = team;
            return this;
        }

        public TimerCard build() {
            return new TimerCard(this);
        }
    }
}