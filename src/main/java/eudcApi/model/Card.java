package eudcApi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eudcApi.rest.jackson.map.DateTimeDeserializer;
import eudcApi.rest.jackson.map.DateTimeSerializer;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;

/**
 * Created by karl on 15.02.16.
 */

@Entity
public class Card {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true, unique = false)
    private String title;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Boolean pinned = false;
    
    @Column(nullable = false)
    private Boolean sendPushAll = false;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime created;

    @ManyToMany(fetch = EAGER, cascade = {PERSIST, MERGE})
    //@Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "Card_User",
            joinColumns = {@JoinColumn(name = "card")},
            inverseJoinColumns = {@JoinColumn(name = "user")},
            uniqueConstraints = @UniqueConstraint(columnNames = {"card", "user"}))
    private List<User> users;

    public Long getId() {
        return id;
    }


    private void setId(Long id) {
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

    public Boolean getPinned() { return pinned; }

    public void setPinned(Boolean pinned) { this.pinned = pinned; }
    
    public Boolean getSendPushAll() { return sendPushAll; }

    public void setSendPushAll(Boolean sendPushAll) { this.sendPushAll = sendPushAll; }

    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getCreated() {
        return created;
    }

    @JsonDeserialize(using = DateTimeDeserializer.class)
    public void setCreated(DateTime created) {
        this.created = created;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
