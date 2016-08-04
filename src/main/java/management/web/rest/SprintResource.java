package management.web.rest;

import com.codahale.metrics.annotation.Timed;
import management.domain.Sprint;
import management.repository.SprintRepository;
import management.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Sprint.
 */
@RestController
@RequestMapping("/api")
public class SprintResource {

    private final Logger log = LoggerFactory.getLogger(SprintResource.class);
        
    @Inject
    private SprintRepository sprintRepository;
    
    /**
     * POST  /sprints : Create a new sprint.
     *
     * @param sprint the sprint to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sprint, or with status 400 (Bad Request) if the sprint has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/sprints",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Sprint> createSprint(@RequestBody Sprint sprint) throws URISyntaxException {
        log.debug("REST request to save Sprint : {}", sprint);
        if (sprint.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("sprint", "idexists", "A new sprint cannot already have an ID")).body(null);
        }
        Sprint result = sprintRepository.save(sprint);
        return ResponseEntity.created(new URI("/api/sprints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("sprint", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sprints : Updates an existing sprint.
     *
     * @param sprint the sprint to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sprint,
     * or with status 400 (Bad Request) if the sprint is not valid,
     * or with status 500 (Internal Server Error) if the sprint couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/sprints",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Sprint> updateSprint(@RequestBody Sprint sprint) throws URISyntaxException {
        log.debug("REST request to update Sprint : {}", sprint);
        if (sprint.getId() == null) {
            return createSprint(sprint);
        }
        Sprint result = sprintRepository.save(sprint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("sprint", sprint.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sprints : get all the sprints.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sprints in body
     */
    @RequestMapping(value = "/sprints",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Sprint> getAllSprints() {
        log.debug("REST request to get all Sprints");
        List<Sprint> sprints = sprintRepository.findAll();
        return sprints;
    }

    /**
     * GET  /sprints/:id : get the "id" sprint.
     *
     * @param id the id of the sprint to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sprint, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/sprints/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Sprint> getSprint(@PathVariable Long id) {
        log.debug("REST request to get Sprint : {}", id);
        Sprint sprint = sprintRepository.findOne(id);
        return Optional.ofNullable(sprint)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /sprints/:id : delete the "id" sprint.
     *
     * @param id the id of the sprint to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/sprints/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSprint(@PathVariable Long id) {
        log.debug("REST request to delete Sprint : {}", id);
        sprintRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("sprint", id.toString())).build();
    }

}
