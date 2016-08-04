package management.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Releasejira.
 */
@Entity
@Table(name = "releasejira")
public class Releasejira implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Releasejira releasejira = (Releasejira) o;
        if(releasejira.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, releasejira.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Releasejira{" +
            "id=" + id +
            ", name='" + name + "'" +
            '}';
    }
}
