package management.web.rest;

import com.codahale.metrics.annotation.Timed;
import management.domain.Efforttype;
import management.repository.EfforttypeRepository;
import management.web.rest.util.HeaderUtil;
import management.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * REST controller for managing Efforttype.
 */
@RestController
@RequestMapping("/api")
public class EfforttypeResource {

    private final Logger log = LoggerFactory.getLogger(EfforttypeResource.class);
        
    @Inject
    private EfforttypeRepository efforttypeRepository;
    
    /**
     * POST  /efforttypes : Create a new efforttype.
     *
     * @param efforttype the efforttype to create
     * @return the ResponseEntity with status 201 (Created) and with body the new efforttype, or with status 400 (Bad Request) if the efforttype has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/efforttypes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Efforttype> createEfforttype(@RequestBody Efforttype efforttype) throws URISyntaxException {
        log.debug("REST request to save Efforttype : {}", efforttype);
        if (efforttype.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("efforttype", "idexists", "A new efforttype cannot already have an ID")).body(null);
        }
        Efforttype result = efforttypeRepository.save(efforttype);
        return ResponseEntity.created(new URI("/api/efforttypes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("efforttype", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /efforttypes : Updates an existing efforttype.
     *
     * @param efforttype the efforttype to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated efforttype,
     * or with status 400 (Bad Request) if the efforttype is not valid,
     * or with status 500 (Internal Server Error) if the efforttype couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/efforttypes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Efforttype> updateEfforttype(@RequestBody Efforttype efforttype) throws URISyntaxException {
        log.debug("REST request to update Efforttype : {}", efforttype);
        if (efforttype.getId() == null) {
            return createEfforttype(efforttype);
        }
        Efforttype result = efforttypeRepository.save(efforttype);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("efforttype", efforttype.getId().toString()))
            .body(result);
    }

    /**
     * GET  /efforttypes : get all the efforttypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of efforttypes in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/efforttypes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Efforttype>> getAllEfforttypes(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Efforttypes");
        Page<Efforttype> page = efforttypeRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/efforttypes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /efforttypes/:id : get the "id" efforttype.
     *
     * @param id the id of the efforttype to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the efforttype, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/efforttypes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Efforttype> getEfforttype(@PathVariable Long id) {
        log.debug("REST request to get Efforttype : {}", id);
        Efforttype efforttype = efforttypeRepository.findOne(id);
        return Optional.ofNullable(efforttype)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /efforttypes/:id : delete the "id" efforttype.
     *
     * @param id the id of the efforttype to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/efforttypes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteEfforttype(@PathVariable Long id) {
        log.debug("REST request to delete Efforttype : {}", id);
        efforttypeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("efforttype", id.toString())).build();
    }

}
