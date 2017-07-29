package eudcApi.model.tabbie;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Round {

    @Id
    @Column
    private Long id;

    @Column
    private String label;

    @Column
    private String motion;

    @Column
    private String infoslide;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime prepStarted;

    @Column
    private String roundLink;

    private Round(Builder builder) {
        id = builder.id;
        label = builder.label;
        motion = builder.motion;
        infoslide = builder.infoslide;
        prepStarted = builder.prepStarted;
        roundLink = builder.roundLink;
    }

    public Round() {
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getMotion() {
        return motion;
    }

    public String getInfoslide() {
        return infoslide;
    }

    public DateTime getPrepStarted() {
        return prepStarted;
    }

    public String getRoundLink() {
        return roundLink;
    }

    public static final class Builder {
        private Long id;
        private String label;
        private String motion;
        private String infoslide;
        private DateTime prepStarted;
        private String roundLink;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder motion(String motion) {
            this.motion = motion;
            return this;
        }

        public Builder infoslide(String infoslide) {
            this.infoslide = infoslide;
            return this;
        }

        public Builder prepStarted(DateTime prepStarted) {
            this.prepStarted = prepStarted;
            return this;
        }

        public Builder roundLink(String roundLink) {
            this.roundLink = roundLink;
            return this;
        }

        public Round build() {
            return new Round(this);
        }
    }
}
