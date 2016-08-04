package management.web.rest;

import com.codahale.metrics.annotation.Timed;
import management.domain.Item;
import management.domain.Manageraccount;
import management.repository.ManageraccountRepository;
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
 * REST controller for managing Manageraccount.
 */
@RestController
@RequestMapping("/api")
public class ManageraccountResource {

    private final Logger log = LoggerFactory.getLogger(ManageraccountResource.class);

    @Inject
    private ManageraccountRepository manageraccountRepository;

    /**
     * POST  /manageraccounts : Create a new manageraccount.
     *
     * @param manageraccount the manageraccount to create
     * @return the ResponseEntity with status 201 (Created) and with body the new manageraccount, or with status 400 (Bad Request) if the manageraccount has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/manageraccounts",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Manageraccount> createManageraccount(@RequestBody Manageraccount manageraccount) throws URISyntaxException {
        log.debug("REST request to save Manageraccount : {}", manageraccount);
        if (manageraccount.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("manageraccount", "idexists", "A new manageraccount cannot already have an ID")).body(null);
        }

        List<Manageraccount> mas = manageraccountRepository.findAll();

        for(Manageraccount ma: mas){
            if (ma.getName().equals(manageraccount.getName())){
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("manageraccount", "idexists", "A new manager account cannot have an existing name")).body(null);
            }
        }

        Manageraccount result = manageraccountRepository.save(manageraccount);
        return ResponseEntity.created(new URI("/api/manageraccounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("manageraccount", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /manageraccounts : Updates an existing manageraccount.
     *
     * @param manageraccount the manageraccount to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated manageraccount,
     * or with status 400 (Bad Request) if the manageraccount is not valid,
     * or with status 500 (Internal Server Error) if the manageraccount couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/manageraccounts",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Manageraccount> updateManageraccount(@RequestBody Manageraccount manageraccount) throws URISyntaxException {
        log.debug("REST request to update Manageraccount : {}", manageraccount);
        if (manageraccount.getId() == null) {
            return createManageraccount(manageraccount);
        }
        Manageraccount result = manageraccountRepository.save(manageraccount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("manageraccount", manageraccount.getId().toString()))
            .body(result);
    }


    /**
     * GET  /manageraccounts : get all the manageraccounts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of manageraccounts in body
     */


    @RequestMapping(value = "/manageraccounts",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Manageraccount> getAllManageraccounts()
    {
        log.debug("REST request to get a page of Manageraccounts");

        List<Manageraccount> pageWithRel = manageraccountRepository.findAllWithEagerRelationships();

        return pageWithRel;
    }





    /**
     * GET  /manageraccounts/:id : get the "id" manageraccount.
     *
     * @param id the id of the manageraccount to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the manageraccount, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/manageraccounts/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Manageraccount> getManageraccount(@PathVariable Long id) {
        log.debug("REST request to get Manageraccount : {}", id);
        Manageraccount manageraccount = manageraccountRepository.findOneWithEagerRelationships(id);
        return Optional.ofNullable(manageraccount)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /manageraccounts/:id : delete the "id" manageraccount.
     *
     * @param id the id of the manageraccount to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/manageraccounts/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteManageraccount(@PathVariable Long id) {
        log.debug("REST request to delete Manageraccount : {}", id);
        manageraccountRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("manageraccount", id.toString())).build();
    }

}
