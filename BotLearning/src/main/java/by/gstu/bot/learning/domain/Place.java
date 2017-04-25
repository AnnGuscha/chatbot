package by.gstu.bot.learning.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Place")
public class Place implements Serializable {

    private static final long serialVersionUID=0;

    @Id
    @Column(name="Id")
    @GeneratedValue
    private Integer id;

    @Column(name="Type")
    private String type;

    @Column(name="Name")
    private String name;

    @Column(name="Description")
    private String description;

    @Column(name="Worktime")
    private String worktime;

    public Place() {
    }

    public Place(String type, String name, String description, String worktime) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.worktime = worktime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description != null ? description : "";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorktime() {
        return worktime != null ? worktime : "";
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (!id.equals(place.id)) return false;
        if (!type.equals(place.type)) return false;
        if (!name.equals(place.name)) return false;
        if (!description.equals(place.description)) return false;
        return worktime.equals(place.worktime);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + worktime.hashCode();
        return result;
    }
}
