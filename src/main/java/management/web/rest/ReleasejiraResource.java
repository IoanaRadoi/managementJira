package management.web.rest;

import com.codahale.metrics.annotation.Timed;
import management.domain.Releasejira;
import management.repository.ReleasejiraRepository;
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
 * REST controller for managing Releasejira.
 */
@RestController
@RequestMapping("/api")
public class ReleasejiraResource {

    private final Logger log = LoggerFactory.getLogger(ReleasejiraResource.class);
        
    @Inject
    private ReleasejiraRepository releasejiraRepository;
    
    /**
     * POST  /releasejiras : Create a new releasejira.
     *
     * @param releasejira the releasejira to create
     * @return the ResponseEntity with status 201 (Created) and with body the new releasejira, or with status 400 (Bad Request) if the releasejira has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/releasejiras",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Releasejira> createReleasejira(@RequestBody Releasejira releasejira) throws URISyntaxException {
        log.debug("REST request to save Releasejira : {}", releasejira);
        if (releasejira.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("releasejira", "idexists", "A new releasejira cannot already have an ID")).body(null);
        }
        Releasejira result = releasejiraRepository.save(releasejira);
        return ResponseEntity.created(new URI("/api/releasejiras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("releasejira", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /releasejiras : Updates an existing releasejira.
     *
     * @param releasejira the releasejira to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated releasejira,
     * or with status 400 (Bad Request) if the releasejira is not valid,
     * or with status 500 (Internal Server Error) if the releasejira couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/releasejiras",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Releasejira> updateReleasejira(@RequestBody Releasejira releasejira) throws URISyntaxException {
        log.debug("REST request to update Releasejira : {}", releasejira);
        if (releasejira.getId() == null) {
            return createReleasejira(releasejira);
        }
        Releasejira result = releasejiraRepository.save(releasejira);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("releasejira", releasejira.getId().toString()))
            .body(result);
    }

    /**
     * GET  /releasejiras : get all the releasejiras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of releasejiras in body
     */
    @RequestMapping(value = "/releasejiras",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Releasejira> getAllReleasejiras() {
        log.debug("REST request to get all Releasejiras");
        List<Releasejira> releasejiras = releasejiraRepository.findAll();
        return releasejiras;
    }

    /**
     * GET  /releasejiras/:id : get the "id" releasejira.
     *
     * @param id the id of the releasejira to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the releasejira, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/releasejiras/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Releasejira> getReleasejira(@PathVariable Long id) {
        log.debug("REST request to get Releasejira : {}", id);
        Releasejira releasejira = releasejiraRepository.findOne(id);
        return Optional.ofNullable(releasejira)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /releasejiras/:id : delete the "id" releasejira.
     *
     * @param id the id of the releasejira to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/releasejiras/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteReleasejira(@PathVariable Long id) {
        log.debug("REST request to delete Releasejira : {}", id);
        releasejiraRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("releasejira", id.toString())).build();
    }

}
