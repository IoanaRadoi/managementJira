package management.web.rest.dto;

import java.io.Serializable;

/**
 * Created by iradoi on 8/5/2016.
 */
public class StoryPointsPerSprintDTO implements Serializable {

    private String year;
    private String release;
    private String project;
    private String sprint;
    private Integer totalStoryPointsPerSprint;
    private Integer sprintCapacity;
        private Long idProjectRelease;

    public StoryPointsPerSprintDTO() {
    }


    public StoryPointsPerSprintDTO(String year, String release, String project, String sprint, Integer totalStoryPointsPerSprint, Integer sprintCapacity, Long idProjectRelease) {
        this.year = year;
        this.release = release;
        this.project = project;
        this.sprint = sprint;
        this.totalStoryPointsPerSprint = totalStoryPointsPerSprint;
        this.sprintCapacity = sprintCapacity;
        this.idProjectRelease = idProjectRelease;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public Integer getTotalStoryPointsPerSprint() {
        return totalStoryPointsPerSprint;
    }

    public void setTotalStoryPointsPerSprint(Integer totalStoryPointsPerSprint) {
        this.totalStoryPointsPerSprint = totalStoryPointsPerSprint;
    }

    public Integer getSprintCapacity() {
        return sprintCapacity;
    }

    public void setSprintCapacity(Integer sprintCapacity) {
        this.sprintCapacity = sprintCapacity;
    }

    public Long getIdProjectRelease() {
        return idProjectRelease;
    }

    public void setIdProjectRelease(Long idProjectRelease) {
        this.idProjectRelease = idProjectRelease;
    }
}
