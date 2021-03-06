package vocibox.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import vocibox.domain.util.CustomDateTimeDeserializer;
import vocibox.domain.util.CustomDateTimeSerializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Expression.
 */
@Entity
@Table(name = "T_EXPRESSION")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Expression implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "expression", nullable = false)
    private String expression;

    @Column(name = "translation", nullable = false)
    private String translation;

    @Column(name = "masculine")
    private Boolean masculine;

    @Column(name = "feminine")
    private Boolean feminine;

    @Column(name = "neuter")
    private Boolean neuter;

    @Column(name = "singular")
    private Boolean singular;

    @Column(name = "plural")
    private Boolean plural;

    @Column(name = "example")
    private String example;

    @Column(name = "definition")
    private String definition;

    @Column(name = "opposite")
    private String opposite;

    @Column(name = "comment")
    private String comment;

    @Column(name = "pronunciation")
    private String pronunciation;

    @Column(name = "image")
    private Boolean image;

    @Column(name = "latitude", precision=10, scale=2)
    private BigDecimal latitude;

    @Column(name = "longitude", precision=10, scale=2)
    private BigDecimal longitude;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "marked", nullable = false)
    private Boolean marked;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tag> tags = new HashSet<>();

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "created_date", nullable = false)
    private DateTime createdDate;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "last_modified_date", nullable = false)
    private DateTime lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Boolean getMasculine() {
        return masculine;
    }

    public void setMasculine(Boolean masculine) {
        this.masculine = masculine;
    }

    public Boolean getFeminine() {
        return feminine;
    }

    public void setFeminine(Boolean feminine) {
        this.feminine = feminine;
    }

    public Boolean getNeuter() {
        return neuter;
    }

    public void setNeuter(Boolean neuter) {
        this.neuter = neuter;
    }

    public Boolean getSingular() {
        return singular;
    }

    public void setSingular(Boolean singular) {
        this.singular = singular;
    }

    public Boolean getPlural() {
        return plural;
    }

    public void setPlural(Boolean plural) {
        this.plural = plural;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getOpposite() {
        return opposite;
    }

    public void setOpposite(String opposite) {
        this.opposite = opposite;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public Boolean getImage() {
        return image;
    }

    public void setImage(Boolean image) {
        this.image = image;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getMarked() {
        return marked;
    }

    public void setMarked(Boolean marked) {
        this.marked = marked;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Expression expression = (Expression) o;

        if (id != null ? !id.equals(expression.id) : expression.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Expression{" +
                "id=" + id +
                ", expression='" + expression + "'" +
                ", translation='" + translation + "'" +
                ", masculine='" + masculine + "'" +
                ", feminine='" + feminine + "'" +
                ", neuter='" + neuter + "'" +
                ", singular='" + singular + "'" +
                ", plural='" + plural + "'" +
                ", example='" + example + "'" +
                ", definition='" + definition + "'" +
                ", opposite='" + opposite + "'" +
                ", comment='" + comment + "'" +
                ", pronunciation='" + pronunciation + "'" +
                ", image='" + image + "'" +
                ", latitude='" + latitude + "'" +
                ", longitude='" + longitude + "'" +
                ", priority='" + priority + "'" +
                ", marked='" + marked + "'" +
                ", createdDate='" + createdDate + "'" +
                ", lastModifiedDate='" + lastModifiedDate + "'" +
                '}';
    }
}
