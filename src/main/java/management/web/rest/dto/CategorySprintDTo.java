package management.web.rest.dto;

import management.domain.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by iradoi on 8/8/2016.
 */
public class CategorySprintDTo {


    private Map<Item, Integer> valuePerCategorie;

    private Long idProjectReleaseSprint;

    public CategorySprintDTo(Map<Item, Integer> valuePerCategorie, Long idProjectReleaseSprint) {
        this.valuePerCategorie = valuePerCategorie;
        this.idProjectReleaseSprint = idProjectReleaseSprint;
    }

    public CategorySprintDTo() {

        this.valuePerCategorie = new HashMap<>();
    }

    public Map<Item, Integer> getValuePerCategorie() {
        return valuePerCategorie;
    }

    public void setValuePerCategorie(Map<Item, Integer> valuePerCategorie) {
        this.valuePerCategorie = valuePerCategorie;
    }

    public Long getIdProjectReleaseSprint() {
        return idProjectReleaseSprint;
    }

    public void setIdProjectReleaseSprint(Long idProjectReleaseSprint) {
        this.idProjectReleaseSprint = idProjectReleaseSprint;
    }
}
