package management.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Projectreleasesprint.
 */
@Entity
@Table(name = "projectreleasesprint")
public class Projectreleasesprint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @ManyToOne
    @NotNull
    private Projectrelease projectrelease;

    @ManyToOne
    @NotNull
    private Sprint sprint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Projectrelease getProjectrelease() {
        return projectrelease;
    }

    public void setProjectrelease(Projectrelease projectrelease) {
        this.projectrelease = projectrelease;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projectreleasesprint projectreleasesprint = (Projectreleasesprint) o;
        if(projectreleasesprint.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectreleasesprint.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectreleasesprint{" +
            "id=" + id +
            ", capacity='" + capacity + "'" +
            '}';
    }
}
