package management.web.rest;

import management.ManagementJiraApp;
import management.domain.Projectrelease;
import management.repository.ProjectreleaseRepository;

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
 * Test class for the ProjectreleaseResource REST controller.
 *
 * @see ProjectreleaseResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ManagementJiraApp.class)
@WebAppConfiguration
@IntegrationTest
public class ProjectreleaseResourceIntTest {


    @Inject
    private ProjectreleaseRepository projectreleaseRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restProjectreleaseMockMvc;

    private Projectrelease projectrelease;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectreleaseResource projectreleaseResource = new ProjectreleaseResource();
        ReflectionTestUtils.setField(projectreleaseResource, "projectreleaseRepository", projectreleaseRepository);
        this.restProjectreleaseMockMvc = MockMvcBuilders.standaloneSetup(projectreleaseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        projectrelease = new Projectrelease();
    }

    @Test
    @Transactional
    public void createProjectrelease() throws Exception {
        int databaseSizeBeforeCreate = projectreleaseRepository.findAll().size();

        // Create the Projectrelease

        restProjectreleaseMockMvc.perform(post("/api/projectreleases")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(projectrelease)))
                .andExpect(status().isCreated());

        // Validate the Projectrelease in the database
        List<Projectrelease> projectreleases = projectreleaseRepository.findAll();
        assertThat(projectreleases).hasSize(databaseSizeBeforeCreate + 1);
        Projectrelease testProjectrelease = projectreleases.get(projectreleases.size() - 1);
    }

    @Test
    @Transactional
    public void getAllProjectreleases() throws Exception {
        // Initialize the database
        projectreleaseRepository.saveAndFlush(projectrelease);

        // Get all the projectreleases
        restProjectreleaseMockMvc.perform(get("/api/projectreleases?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(projectrelease.getId().intValue())));
    }

    @Test
    @Transactional
    public void getProjectrelease() throws Exception {
        // Initialize the database
        projectreleaseRepository.saveAndFlush(projectrelease);

        // Get the projectrelease
        restProjectreleaseMockMvc.perform(get("/api/projectreleases/{id}", projectrelease.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(projectrelease.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProjectrelease() throws Exception {
        // Get the projectrelease
        restProjectreleaseMockMvc.perform(get("/api/projectreleases/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectrelease() throws Exception {
        // Initialize the database
        projectreleaseRepository.saveAndFlush(projectrelease);
        int databaseSizeBeforeUpdate = projectreleaseRepository.findAll().size();

        // Update the projectrelease
        Projectrelease updatedProjectrelease = new Projectrelease();
        updatedProjectrelease.setId(projectrelease.getId());

        restProjectreleaseMockMvc.perform(put("/api/projectreleases")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedProjectrelease)))
                .andExpect(status().isOk());

        // Validate the Projectrelease in the database
        List<Projectrelease> projectreleases = projectreleaseRepository.findAll();
        assertThat(projectreleases).hasSize(databaseSizeBeforeUpdate);
        Projectrelease testProjectrelease = projectreleases.get(projectreleases.size() - 1);
    }

    @Test
    @Transactional
    public void deleteProjectrelease() throws Exception {
        // Initialize the database
        projectreleaseRepository.saveAndFlush(projectrelease);
        int databaseSizeBeforeDelete = projectreleaseRepository.findAll().size();

        // Get the projectrelease
        restProjectreleaseMockMvc.perform(delete("/api/projectreleases/{id}", projectrelease.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectrelease> projectreleases = projectreleaseRepository.findAll();
        assertThat(projectreleases).hasSize(databaseSizeBeforeDelete - 1);
    }
}
