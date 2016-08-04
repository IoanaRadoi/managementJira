package management.web.rest;

import management.ManagementJiraApp;
import management.domain.Manageraccount;
import management.repository.ManageraccountRepository;

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
 * Test class for the ManageraccountResource REST controller.
 *
 * @see ManageraccountResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ManagementJiraApp.class)
@WebAppConfiguration
@IntegrationTest
public class ManageraccountResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_PASSWORD = "AAAAA";
    private static final String UPDATED_PASSWORD = "BBBBB";
    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";

    @Inject
    private ManageraccountRepository manageraccountRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restManageraccountMockMvc;

    private Manageraccount manageraccount;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ManageraccountResource manageraccountResource = new ManageraccountResource();
        ReflectionTestUtils.setField(manageraccountResource, "manageraccountRepository", manageraccountRepository);
        this.restManageraccountMockMvc = MockMvcBuilders.standaloneSetup(manageraccountResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        manageraccount = new Manageraccount();
        manageraccount.setName(DEFAULT_NAME);
        manageraccount.setPassword(DEFAULT_PASSWORD);
        manageraccount.setStatus(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createManageraccount() throws Exception {
        int databaseSizeBeforeCreate = manageraccountRepository.findAll().size();

        // Create the Manageraccount

        restManageraccountMockMvc.perform(post("/api/manageraccounts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(manageraccount)))
                .andExpect(status().isCreated());

        // Validate the Manageraccount in the database
        List<Manageraccount> manageraccounts = manageraccountRepository.findAll();
        assertThat(manageraccounts).hasSize(databaseSizeBeforeCreate + 1);
        Manageraccount testManageraccount = manageraccounts.get(manageraccounts.size() - 1);
        assertThat(testManageraccount.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testManageraccount.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testManageraccount.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void getAllManageraccounts() throws Exception {
        // Initialize the database
        manageraccountRepository.saveAndFlush(manageraccount);

        // Get all the manageraccounts
        restManageraccountMockMvc.perform(get("/api/manageraccounts?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(manageraccount.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getManageraccount() throws Exception {
        // Initialize the database
        manageraccountRepository.saveAndFlush(manageraccount);

        // Get the manageraccount
        restManageraccountMockMvc.perform(get("/api/manageraccounts/{id}", manageraccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(manageraccount.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingManageraccount() throws Exception {
        // Get the manageraccount
        restManageraccountMockMvc.perform(get("/api/manageraccounts/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManageraccount() throws Exception {
        // Initialize the database
        manageraccountRepository.saveAndFlush(manageraccount);
        int databaseSizeBeforeUpdate = manageraccountRepository.findAll().size();

        // Update the manageraccount
        Manageraccount updatedManageraccount = new Manageraccount();
        updatedManageraccount.setId(manageraccount.getId());
        updatedManageraccount.setName(UPDATED_NAME);
        updatedManageraccount.setPassword(UPDATED_PASSWORD);
        updatedManageraccount.setStatus(UPDATED_STATUS);

        restManageraccountMockMvc.perform(put("/api/manageraccounts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedManageraccount)))
                .andExpect(status().isOk());

        // Validate the Manageraccount in the database
        List<Manageraccount> manageraccounts = manageraccountRepository.findAll();
        assertThat(manageraccounts).hasSize(databaseSizeBeforeUpdate);
        Manageraccount testManageraccount = manageraccounts.get(manageraccounts.size() - 1);
        assertThat(testManageraccount.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testManageraccount.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testManageraccount.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteManageraccount() throws Exception {
        // Initialize the database
        manageraccountRepository.saveAndFlush(manageraccount);
        int databaseSizeBeforeDelete = manageraccountRepository.findAll().size();

        // Get the manageraccount
        restManageraccountMockMvc.perform(delete("/api/manageraccounts/{id}", manageraccount.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Manageraccount> manageraccounts = manageraccountRepository.findAll();
        assertThat(manageraccounts).hasSize(databaseSizeBeforeDelete - 1);
    }
}
