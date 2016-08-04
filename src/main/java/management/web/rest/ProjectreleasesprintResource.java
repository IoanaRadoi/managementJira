package management.web.rest;

import com.codahale.metrics.annotation.Timed;
import management.domain.Projectreleasesprint;
import management.repository.ProjectreleasesprintRepository;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Projectreleasesprint.
 */
@RestController
@RequestMapping("/api")
public class ProjectreleasesprintResource {

    private final Logger log = LoggerFactory.getLogger(ProjectreleasesprintResource.class);
        
    @Inject
    private ProjectreleasesprintRepository projectreleasesprintRepository;
    
    /**
     * POST  /projectreleasesprints : Create a new projectreleasesprint.
     *
     * @param projectreleasesprint the projectreleasesprint to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectreleasesprint, or with status 400 (Bad Request) if the projectreleasesprint has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/projectreleasesprints",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Projectreleasesprint> createProjectreleasesprint(@Valid @RequestBody Projectreleasesprint projectreleasesprint) throws URISyntaxException {
        log.debug("REST request to save Projectreleasesprint : {}", projectreleasesprint);
        if (projectreleasesprint.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("projectreleasesprint", "idexists", "A new projectreleasesprint cannot already have an ID")).body(null);
        }
        Projectreleasesprint result = projectreleasesprintRepository.save(projectreleasesprint);
        return ResponseEntity.created(new URI("/api/projectreleasesprints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("projectreleasesprint", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectreleasesprints : Updates an existing projectreleasesprint.
     *
     * @param projectreleasesprint the projectreleasesprint to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectreleasesprint,
     * or with status 400 (Bad Request) if the projectreleasesprint is not valid,
     * or with status 500 (Internal Server Error) if the projectreleasesprint couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/projectreleasesprints",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Projectreleasesprint> updateProjectreleasesprint(@Valid @RequestBody Projectreleasesprint projectreleasesprint) throws URISyntaxException {
        log.debug("REST request to update Projectreleasesprint : {}", projectreleasesprint);
        if (projectreleasesprint.getId() == null) {
            return createProjectreleasesprint(projectreleasesprint);
        }
        Projectreleasesprint result = projectreleasesprintRepository.save(projectreleasesprint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("projectreleasesprint", projectreleasesprint.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectreleasesprints : get all the projectreleasesprints.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectreleasesprints in body
     */
    @RequestMapping(value = "/projectreleasesprints",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Projectreleasesprint> getAllProjectreleasesprints() {
        log.debug("REST request to get all Projectreleasesprints");
        List<Projectreleasesprint> projectreleasesprints = projectreleasesprintRepository.findAll();
        return projectreleasesprints;
    }

    /**
     * GET  /projectreleasesprints/:id : get the "id" projectreleasesprint.
     *
     * @param id the id of the projectreleasesprint to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectreleasesprint, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/projectreleasesprints/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Projectreleasesprint> getProjectreleasesprint(@PathVariable Long id) {
        log.debug("REST request to get Projectreleasesprint : {}", id);
        Projectreleasesprint projectreleasesprint = projectreleasesprintRepository.findOne(id);
        return Optional.ofNullable(projectreleasesprint)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /projectreleasesprints/:id : delete the "id" projectreleasesprint.
     *
     * @param id the id of the projectreleasesprint to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/projectreleasesprints/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteProjectreleasesprint(@PathVariable Long id) {
        log.debug("REST request to delete Projectreleasesprint : {}", id);
        projectreleasesprintRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("projectreleasesprint", id.toString())).build();
    }

}
