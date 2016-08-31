package management.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Manageraccount.
 */
@Entity
@Table(name = "manageraccount")
public class Manageraccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;  //todo DE MODIFICAT IN FIRST NAME

    @Column(name = "password")
    private String password;   //TODO DE MODIFICAT IN LAST NAME

    @Column(name = "status")
    private String status;

    @ManyToMany
    @JoinTable(name = "manageraccount_project",
               joinColumns = @JoinColumn(name="manageraccounts_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="projects_id", referencedColumnName="ID"))
    private Set<Project> projects = new HashSet<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Manageraccount manageraccount = (Manageraccount) o;
        if(manageraccount.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, manageraccount.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Manageraccount{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", password='" + password + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
