package management.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Storypoint.
 */
@Entity
@Table(name = "storypoint")
public class Storypoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "storypoint", nullable = false)
    private Integer storypoint;

    @ManyToOne
    @NotNull
    private Projectreleasesprint projectreleasesprint;

    @ManyToOne
    @NotNull
    private Efforttype efforttype;

    @ManyToOne
    @NotNull
    private Item item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStorypoint() {
        return storypoint;
    }

    public void setStorypoint(Integer storypoint) {
        this.storypoint = storypoint;
    }

    public Projectreleasesprint getProjectreleasesprint() {
        return projectreleasesprint;
    }

    public void setProjectreleasesprint(Projectreleasesprint projectreleasesprint) {
        this.projectreleasesprint = projectreleasesprint;
    }

    public Efforttype getEfforttype() {
        return efforttype;
    }

    public void setEfforttype(Efforttype efforttype) {
        this.efforttype = efforttype;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Storypoint storypoint = (Storypoint) o;
        if(storypoint.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, storypoint.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Storypoint{" +
            "id=" + id +
            ", storypoint='" + storypoint + "'" +
            '}';
    }
}
