package management.web.rest;

import com.codahale.metrics.annotation.Timed;
import management.domain.Projectrelease;
import management.repository.ProjectreleaseRepository;
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
 * REST controller for managing Projectrelease.
 */
@RestController
@RequestMapping("/api")
public class ProjectreleaseResource {

    private final Logger log = LoggerFactory.getLogger(ProjectreleaseResource.class);
        
    @Inject
    private ProjectreleaseRepository projectreleaseRepository;
    
    /**
     * POST  /projectreleases : Create a new projectrelease.
     *
     * @param projectrelease the projectrelease to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectrelease, or with status 400 (Bad Request) if the projectrelease has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/projectreleases",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Projectrelease> createProjectrelease(@RequestBody Projectrelease projectrelease) throws URISyntaxException {
        log.debug("REST request to save Projectrelease : {}", projectrelease);
        if (projectrelease.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("projectrelease", "idexists", "A new projectrelease cannot already have an ID")).body(null);
        }
        Projectrelease result = projectreleaseRepository.save(projectrelease);
        return ResponseEntity.created(new URI("/api/projectreleases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("projectrelease", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectreleases : Updates an existing projectrelease.
     *
     * @param projectrelease the projectrelease to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectrelease,
     * or with status 400 (Bad Request) if the projectrelease is not valid,
     * or with status 500 (Internal Server Error) if the projectrelease couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/projectreleases",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Projectrelease> updateProjectrelease(@RequestBody Projectrelease projectrelease) throws URISyntaxException {
        log.debug("REST request to update Projectrelease : {}", projectrelease);
        if (projectrelease.getId() == null) {
            return createProjectrelease(projectrelease);
        }
        Projectrelease result = projectreleaseRepository.save(projectrelease);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("projectrelease", projectrelease.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectreleases : get all the projectreleases.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectreleases in body
     */
    @RequestMapping(value = "/projectreleases",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Projectrelease> getAllProjectreleases() {
        log.debug("REST request to get all Projectreleases");
        List<Projectrelease> projectreleases = projectreleaseRepository.findAll();
        return projectreleases;
    }

    /**
     * GET  /projectreleases/:id : get the "id" projectrelease.
     *
     * @param id the id of the projectrelease to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectrelease, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/projectreleases/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Projectrelease> getProjectrelease(@PathVariable Long id) {
        log.debug("REST request to get Projectrelease : {}", id);
        Projectrelease projectrelease = projectreleaseRepository.findOne(id);
        return Optional.ofNullable(projectrelease)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /projectreleases/:id : delete the "id" projectrelease.
     *
     * @param id the id of the projectrelease to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/projectreleases/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteProjectrelease(@PathVariable Long id) {
        log.debug("REST request to delete Projectrelease : {}", id);
        projectreleaseRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("projectrelease", id.toString())).build();
    }

}
