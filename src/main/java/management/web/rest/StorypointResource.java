package management.web.rest;

import com.codahale.metrics.annotation.Timed;
import management.domain.Item;
import management.domain.Project;
import management.domain.Projectreleasesprint;
import management.domain.Storypoint;
import management.repository.ItemRepository;
import management.repository.ProjectRepository;
import management.repository.ProjectreleasesprintRepository;
import management.repository.StorypointRepository;
import management.web.rest.dto.CategorySprintDTo;
import management.web.rest.dto.StoryPointsPerSprintDTO;
import management.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Storypoint.
 */
@RestController
@RequestMapping("/api")
public class StorypointResource {

    private final Logger log = LoggerFactory.getLogger(StorypointResource.class);

    @Inject
    private StorypointRepository storypointRepository;

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private ItemRepository itemRepository;

    @Inject
    private ProjectreleasesprintRepository projectreleasesprintRepository;

    /**
     * POST  /storypoints : Create a new storypoint.
     *
     * @param storypoint the storypoint to create
     * @return the ResponseEntity with status 201 (Created) and with body the new storypoint, or with status 400 (Bad Request) if the storypoint has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/storypoints",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Storypoint> createStorypoint(@Valid @RequestBody Storypoint storypoint) throws URISyntaxException {
        log.debug("REST request to save Storypoint : {}", storypoint);
        if (storypoint.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("storypoint", "idexists", "A new storypoint cannot already have an ID")).body(null);
        }
        Storypoint result = storypointRepository.save(storypoint);
        return ResponseEntity.created(new URI("/api/storypoints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("storypoint", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /storypoints : Updates an existing storypoint.
     *
     * @param storypoint the storypoint to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated storypoint,
     * or with status 400 (Bad Request) if the storypoint is not valid,
     * or with status 500 (Internal Server Error) if the storypoint couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/storypoints",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Storypoint> updateStorypoint(@Valid @RequestBody Storypoint storypoint) throws URISyntaxException {
        log.debug("REST request to update Storypoint : {}", storypoint);
        if (storypoint.getId() == null) {
            return createStorypoint(storypoint);
        }
        Storypoint result = storypointRepository.save(storypoint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("storypoint", storypoint.getId().toString()))
            .body(result);
    }

    /**
     * GET  /storypoints : get all the storypoints.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of storypoints in body
     */
    @RequestMapping(value = "/storypoints",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Storypoint> getAllStorypoints() {
        log.debug("REST request to get all Storypoints");
        List<Storypoint> storypoints = storypointRepository.findAll();
        //List<Storypoint> storypointFiltered =  storypointRepository.countStoryPointPerSprint(Long.valueOf(1));

        return storypoints;
    }




    @RequestMapping(value = "/storypointsDetailsPerSprint",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CategorySprintDTo> getAllStorypointsPerSprint() {

        List<Projectreleasesprint> projectreleasesprints = projectreleasesprintRepository.findAll();
        List<Item> categories = itemRepository.findAll();

        List<CategorySprintDTo> categoriesPerSprint = new ArrayList<>();

        for(Projectreleasesprint prs: projectreleasesprints){
            CategorySprintDTo categorySprintDTo = new CategorySprintDTo();

            for(Item item:categories) {
                Integer sum = storypointRepository.sumStoryPointPerSprintGroupByItem(prs.getId(), item.getId());


                categorySprintDTo.getValuePerCategorie().put(item,sum);

            }

            categorySprintDTo.setIdProjectReleaseSprint(prs.getId());



        }


        return categoriesPerSprint;
    }


    @RequestMapping(value = "/storypointsPerSprint",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<StoryPointsPerSprintDTO> getTotalStorypointsPerSprint() {


        List<StoryPointsPerSprintDTO>  storyPointsPerSprintDTOs = new ArrayList<>();
        for(Projectreleasesprint projectreleasesprint: projectreleasesprintRepository.findAll()){

            Integer totalStoryPointPerSprint = storypointRepository.countStoryPointPerSprint(projectreleasesprint.getId());
            if (totalStoryPointPerSprint!=null) {
                storyPointsPerSprintDTOs.add(new StoryPointsPerSprintDTO(projectreleasesprint.getProjectrelease().getYear().getYear(), projectreleasesprint.getProjectrelease().getReleasejira().getName(), projectreleasesprint.getProjectrelease().getProject().getName(), projectreleasesprint.getSprint().getName(), totalStoryPointPerSprint, projectreleasesprint.getCapacity(), projectreleasesprint.getProjectrelease().getId()));
            }
        }

        return storyPointsPerSprintDTOs;
    }

    /**
     * GET  /storypoints/:id : get the "id" storypoint.
     *
     * @param id the id of the storypoint to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the storypoint, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/storypoints/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Storypoint> getStorypoint(@PathVariable Long id) {
        log.debug("REST request to get Storypoint : {}", id);
        Storypoint storypoint = storypointRepository.findOne(id);
        return Optional.ofNullable(storypoint)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /storypoints/:id : delete the "id" storypoint.
     *
     * @param id the id of the storypoint to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/storypoints/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteStorypoint(@PathVariable Long id) {
        log.debug("REST request to delete Storypoint : {}", id);
        storypointRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("storypoint", id.toString())).build();
    }

}
