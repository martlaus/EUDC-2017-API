package eudcApi.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eudcApi.rest.jackson.map.DateTimeDeserializer;
import eudcApi.rest.jackson.map.DateTimeSerializer;


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
	
	@Column(nullable = true, unique = false)
    private String title;

    @Column(nullable = true)
    private String description;
    
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
    
}
