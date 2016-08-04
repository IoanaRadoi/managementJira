package management.web.rest;

import management.ManagementJiraApp;
import management.domain.Efforttype;
import management.repository.EfforttypeRepository;

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
 * Test class for the EfforttypeResource REST controller.
 *
 * @see EfforttypeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ManagementJiraApp.class)
@WebAppConfiguration
@IntegrationTest
public class EfforttypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    @Inject
    private EfforttypeRepository efforttypeRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restEfforttypeMockMvc;

    private Efforttype efforttype;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EfforttypeResource efforttypeResource = new EfforttypeResource();
        ReflectionTestUtils.setField(efforttypeResource, "efforttypeRepository", efforttypeRepository);
        this.restEfforttypeMockMvc = MockMvcBuilders.standaloneSetup(efforttypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        efforttype = new Efforttype();
        efforttype.setName(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createEfforttype() throws Exception {
        int databaseSizeBeforeCreate = efforttypeRepository.findAll().size();

        // Create the Efforttype

        restEfforttypeMockMvc.perform(post("/api/efforttypes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(efforttype)))
                .andExpect(status().isCreated());

        // Validate the Efforttype in the database
        List<Efforttype> efforttypes = efforttypeRepository.findAll();
        assertThat(efforttypes).hasSize(databaseSizeBeforeCreate + 1);
        Efforttype testEfforttype = efforttypes.get(efforttypes.size() - 1);
        assertThat(testEfforttype.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void getAllEfforttypes() throws Exception {
        // Initialize the database
        efforttypeRepository.saveAndFlush(efforttype);

        // Get all the efforttypes
        restEfforttypeMockMvc.perform(get("/api/efforttypes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(efforttype.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getEfforttype() throws Exception {
        // Initialize the database
        efforttypeRepository.saveAndFlush(efforttype);

        // Get the efforttype
        restEfforttypeMockMvc.perform(get("/api/efforttypes/{id}", efforttype.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(efforttype.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEfforttype() throws Exception {
        // Get the efforttype
        restEfforttypeMockMvc.perform(get("/api/efforttypes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEfforttype() throws Exception {
        // Initialize the database
        efforttypeRepository.saveAndFlush(efforttype);
        int databaseSizeBeforeUpdate = efforttypeRepository.findAll().size();

        // Update the efforttype
        Efforttype updatedEfforttype = new Efforttype();
        updatedEfforttype.setId(efforttype.getId());
        updatedEfforttype.setName(UPDATED_NAME);

        restEfforttypeMockMvc.perform(put("/api/efforttypes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedEfforttype)))
                .andExpect(status().isOk());

        // Validate the Efforttype in the database
        List<Efforttype> efforttypes = efforttypeRepository.findAll();
        assertThat(efforttypes).hasSize(databaseSizeBeforeUpdate);
        Efforttype testEfforttype = efforttypes.get(efforttypes.size() - 1);
        assertThat(testEfforttype.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void deleteEfforttype() throws Exception {
        // Initialize the database
        efforttypeRepository.saveAndFlush(efforttype);
        int databaseSizeBeforeDelete = efforttypeRepository.findAll().size();

        // Get the efforttype
        restEfforttypeMockMvc.perform(delete("/api/efforttypes/{id}", efforttype.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Efforttype> efforttypes = efforttypeRepository.findAll();
        assertThat(efforttypes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
