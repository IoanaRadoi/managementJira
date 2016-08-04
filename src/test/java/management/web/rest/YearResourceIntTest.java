package management.web.rest;

import management.ManagementJiraApp;
import management.domain.Year;
import management.repository.YearRepository;

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
 * Test class for the YearResource REST controller.
 *
 * @see YearResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ManagementJiraApp.class)
@WebAppConfiguration
@IntegrationTest
public class YearResourceIntTest {

    private static final String DEFAULT_YEAR = "AAAA";
    private static final String UPDATED_YEAR = "BBBB";

    @Inject
    private YearRepository yearRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restYearMockMvc;

    private Year year;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        YearResource yearResource = new YearResource();
        ReflectionTestUtils.setField(yearResource, "yearRepository", yearRepository);
        this.restYearMockMvc = MockMvcBuilders.standaloneSetup(yearResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        year = new Year();
        year.setYear(DEFAULT_YEAR);
    }

    @Test
    @Transactional
    public void createYear() throws Exception {
        int databaseSizeBeforeCreate = yearRepository.findAll().size();

        // Create the Year

        restYearMockMvc.perform(post("/api/years")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(year)))
                .andExpect(status().isCreated());

        // Validate the Year in the database
        List<Year> years = yearRepository.findAll();
        assertThat(years).hasSize(databaseSizeBeforeCreate + 1);
        Year testYear = years.get(years.size() - 1);
        assertThat(testYear.getYear()).isEqualTo(DEFAULT_YEAR);
    }

    @Test
    @Transactional
    public void getAllYears() throws Exception {
        // Initialize the database
        yearRepository.saveAndFlush(year);

        // Get all the years
        restYearMockMvc.perform(get("/api/years?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(year.getId().intValue())))
                .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.toString())));
    }

    @Test
    @Transactional
    public void getYear() throws Exception {
        // Initialize the database
        yearRepository.saveAndFlush(year);

        // Get the year
        restYearMockMvc.perform(get("/api/years/{id}", year.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(year.getId().intValue()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingYear() throws Exception {
        // Get the year
        restYearMockMvc.perform(get("/api/years/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateYear() throws Exception {
        // Initialize the database
        yearRepository.saveAndFlush(year);
        int databaseSizeBeforeUpdate = yearRepository.findAll().size();

        // Update the year
        Year updatedYear = new Year();
        updatedYear.setId(year.getId());
        updatedYear.setYear(UPDATED_YEAR);

        restYearMockMvc.perform(put("/api/years")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedYear)))
                .andExpect(status().isOk());

        // Validate the Year in the database
        List<Year> years = yearRepository.findAll();
        assertThat(years).hasSize(databaseSizeBeforeUpdate);
        Year testYear = years.get(years.size() - 1);
        assertThat(testYear.getYear()).isEqualTo(UPDATED_YEAR);
    }

    @Test
    @Transactional
    public void deleteYear() throws Exception {
        // Initialize the database
        yearRepository.saveAndFlush(year);
        int databaseSizeBeforeDelete = yearRepository.findAll().size();

        // Get the year
        restYearMockMvc.perform(delete("/api/years/{id}", year.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Year> years = yearRepository.findAll();
        assertThat(years).hasSize(databaseSizeBeforeDelete - 1);
    }
}
