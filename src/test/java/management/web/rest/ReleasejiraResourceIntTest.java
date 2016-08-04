package management.web.rest;

import management.ManagementJiraApp;
import management.domain.Releasejira;
import management.repository.ReleasejiraRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the ReleasejiraResource REST controller.
 *
 * @see ReleasejiraResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ManagementJiraApp.class)
@WebAppConfiguration
@IntegrationTest
public class ReleasejiraResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    @Inject
    private ReleasejiraRepository releasejiraRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restReleasejiraMockMvc;

    private Releasejira releasejira;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReleasejiraResource releasejiraResource = new ReleasejiraResource();
        ReflectionTestUtils.setField(releasejiraResource, "releasejiraRepository", releasejiraRepository);
        this.restReleasejiraMockMvc = MockMvcBuilders.standaloneSetup(releasejiraResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        releasejira = new Releasejira();
        releasejira.setName(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createReleasejira() throws Exception {
        int databaseSizeBeforeCreate = releasejiraRepository.findAll().size();

        // Create the Releasejira

        restReleasejiraMockMvc.perform(post("/api/releasejiras")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(releasejira)))
                .andExpect(status().isCreated());

        // Validate the Releasejira in the database
        List<Releasejira> releasejiras = releasejiraRepository.findAll();
        assertThat(releasejiras).hasSize(databaseSizeBeforeCreate + 1);
        Releasejira testReleasejira = releasejiras.get(releasejiras.size() - 1);
        assertThat(testReleasejira.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void getAllReleasejiras() throws Exception {
        // Initialize the database
        releasejiraRepository.saveAndFlush(releasejira);

        // Get all the releasejiras
        restReleasejiraMockMvc.perform(get("/api/releasejiras?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(releasejira.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getReleasejira() throws Exception {
        // Initialize the database
        releasejiraRepository.saveAndFlush(releasejira);

        // Get the releasejira
        restReleasejiraMockMvc.perform(get("/api/releasejiras/{id}", releasejira.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(releasejira.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReleasejira() throws Exception {
        // Get the releasejira
        restReleasejiraMockMvc.perform(get("/api/releasejiras/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReleasejira() throws Exception {
        // Initialize the database
        releasejiraRepository.saveAndFlush(releasejira);
        int databaseSizeBeforeUpdate = releasejiraRepository.findAll().size();

        // Update the releasejira
        Releasejira updatedReleasejira = new Releasejira();
        updatedReleasejira.setId(releasejira.getId());
        updatedReleasejira.setName(UPDATED_NAME);

        restReleasejiraMockMvc.perform(put("/api/releasejiras")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedReleasejira)))
                .andExpect(status().isOk());

        // Validate the Releasejira in the database
        List<Releasejira> releasejiras = releasejiraRepository.findAll();
        assertThat(releasejiras).hasSize(databaseSizeBeforeUpdate);
        Releasejira testReleasejira = releasejiras.get(releasejiras.size() - 1);
        assertThat(testReleasejira.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void deleteReleasejira() throws Exception {
        // Initialize the database
        releasejiraRepository.saveAndFlush(releasejira);
        int databaseSizeBeforeDelete = releasejiraRepository.findAll().size();

        // Get the releasejira
        restReleasejiraMockMvc.perform(delete("/api/releasejiras/{id}", releasejira.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Releasejira> releasejiras = releasejiraRepository.findAll();
        assertThat(releasejiras).hasSize(databaseSizeBeforeDelete - 1);
    }
}
