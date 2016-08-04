package management.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Projectrelease.
 */
@Entity
@Table(name = "projectrelease")
public class Projectrelease implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Releasejira releasejira;

    @ManyToOne
    private Year year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Releasejira getReleasejira() {
        return releasejira;
    }

    public void setReleasejira(Releasejira releasejira) {
        this.releasejira = releasejira;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projectrelease projectrelease = (Projectrelease) o;
        if(projectrelease.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectrelease.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectrelease{" +
            "id=" + id +
            '}';
    }
}
