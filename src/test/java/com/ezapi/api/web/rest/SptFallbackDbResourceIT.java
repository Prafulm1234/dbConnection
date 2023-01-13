package com.ezapi.api.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ezapi.api.IntegrationTest;
import com.ezapi.api.domain.SptFallbackDb;
import com.ezapi.api.repository.SptFallbackDbRepository;
import com.ezapi.api.service.dto.SptFallbackDbDTO;
import com.ezapi.api.service.mapper.SptFallbackDbMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SptFallbackDbResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SptFallbackDbResourceIT {

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DBID = "AAAAAAAAAA";
    private static final String UPDATED_DBID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/spt-fallback-dbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SptFallbackDbRepository sptFallbackDbRepository;

    @Autowired
    private SptFallbackDbMapper sptFallbackDbMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSptFallbackDbMockMvc;

    private SptFallbackDb sptFallbackDb;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SptFallbackDb createEntity(EntityManager em) {
        SptFallbackDb sptFallbackDb = new SptFallbackDb().version(DEFAULT_VERSION).name(DEFAULT_NAME).dbid(DEFAULT_DBID);
        return sptFallbackDb;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SptFallbackDb createUpdatedEntity(EntityManager em) {
        SptFallbackDb sptFallbackDb = new SptFallbackDb().version(UPDATED_VERSION).name(UPDATED_NAME).dbid(UPDATED_DBID);
        return sptFallbackDb;
    }

    @BeforeEach
    public void initTest() {
        sptFallbackDb = createEntity(em);
    }

    @Test
    @Transactional
    void createSptFallbackDb() throws Exception {
        int databaseSizeBeforeCreate = sptFallbackDbRepository.findAll().size();
        // Create the SptFallbackDb
        SptFallbackDbDTO sptFallbackDbDTO = sptFallbackDbMapper.toDto(sptFallbackDb);
        restSptFallbackDbMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sptFallbackDbDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SptFallbackDb in the database
        List<SptFallbackDb> sptFallbackDbList = sptFallbackDbRepository.findAll();
        assertThat(sptFallbackDbList).hasSize(databaseSizeBeforeCreate + 1);
        SptFallbackDb testSptFallbackDb = sptFallbackDbList.get(sptFallbackDbList.size() - 1);
        assertThat(testSptFallbackDb.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testSptFallbackDb.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSptFallbackDb.getDbid()).isEqualTo(DEFAULT_DBID);
    }

    @Test
    @Transactional
    void createSptFallbackDbWithExistingId() throws Exception {
        // Create the SptFallbackDb with an existing ID
        sptFallbackDb.setId(1L);
        SptFallbackDbDTO sptFallbackDbDTO = sptFallbackDbMapper.toDto(sptFallbackDb);

        int databaseSizeBeforeCreate = sptFallbackDbRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSptFallbackDbMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sptFallbackDbDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SptFallbackDb in the database
        List<SptFallbackDb> sptFallbackDbList = sptFallbackDbRepository.findAll();
        assertThat(sptFallbackDbList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSptFallbackDbs() throws Exception {
        // Initialize the database
        sptFallbackDbRepository.saveAndFlush(sptFallbackDb);

        // Get all the sptFallbackDbList
        restSptFallbackDbMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sptFallbackDb.getId().intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].dbid").value(hasItem(DEFAULT_DBID)));
    }

    @Test
    @Transactional
    void getSptFallbackDb() throws Exception {
        // Initialize the database
        sptFallbackDbRepository.saveAndFlush(sptFallbackDb);

        // Get the sptFallbackDb
        restSptFallbackDbMockMvc
            .perform(get(ENTITY_API_URL_ID, sptFallbackDb.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sptFallbackDb.getId().intValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.dbid").value(DEFAULT_DBID));
    }

    @Test
    @Transactional
    void getNonExistingSptFallbackDb() throws Exception {
        // Get the sptFallbackDb
        restSptFallbackDbMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSptFallbackDb() throws Exception {
        // Initialize the database
        sptFallbackDbRepository.saveAndFlush(sptFallbackDb);

        int databaseSizeBeforeUpdate = sptFallbackDbRepository.findAll().size();

        // Update the sptFallbackDb
        SptFallbackDb updatedSptFallbackDb = sptFallbackDbRepository.findById(sptFallbackDb.getId()).get();
        // Disconnect from session so that the updates on updatedSptFallbackDb are not directly saved in db
        em.detach(updatedSptFallbackDb);
        updatedSptFallbackDb.version(UPDATED_VERSION).name(UPDATED_NAME).dbid(UPDATED_DBID);
        SptFallbackDbDTO sptFallbackDbDTO = sptFallbackDbMapper.toDto(updatedSptFallbackDb);

        restSptFallbackDbMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sptFallbackDbDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sptFallbackDbDTO))
            )
            .andExpect(status().isOk());

        // Validate the SptFallbackDb in the database
        List<SptFallbackDb> sptFallbackDbList = sptFallbackDbRepository.findAll();
        assertThat(sptFallbackDbList).hasSize(databaseSizeBeforeUpdate);
        SptFallbackDb testSptFallbackDb = sptFallbackDbList.get(sptFallbackDbList.size() - 1);
        assertThat(testSptFallbackDb.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testSptFallbackDb.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSptFallbackDb.getDbid()).isEqualTo(UPDATED_DBID);
    }

    @Test
    @Transactional
    void putNonExistingSptFallbackDb() throws Exception {
        int databaseSizeBeforeUpdate = sptFallbackDbRepository.findAll().size();
        sptFallbackDb.setId(count.incrementAndGet());

        // Create the SptFallbackDb
        SptFallbackDbDTO sptFallbackDbDTO = sptFallbackDbMapper.toDto(sptFallbackDb);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSptFallbackDbMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sptFallbackDbDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sptFallbackDbDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SptFallbackDb in the database
        List<SptFallbackDb> sptFallbackDbList = sptFallbackDbRepository.findAll();
        assertThat(sptFallbackDbList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSptFallbackDb() throws Exception {
        int databaseSizeBeforeUpdate = sptFallbackDbRepository.findAll().size();
        sptFallbackDb.setId(count.incrementAndGet());

        // Create the SptFallbackDb
        SptFallbackDbDTO sptFallbackDbDTO = sptFallbackDbMapper.toDto(sptFallbackDb);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSptFallbackDbMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sptFallbackDbDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SptFallbackDb in the database
        List<SptFallbackDb> sptFallbackDbList = sptFallbackDbRepository.findAll();
        assertThat(sptFallbackDbList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSptFallbackDb() throws Exception {
        int databaseSizeBeforeUpdate = sptFallbackDbRepository.findAll().size();
        sptFallbackDb.setId(count.incrementAndGet());

        // Create the SptFallbackDb
        SptFallbackDbDTO sptFallbackDbDTO = sptFallbackDbMapper.toDto(sptFallbackDb);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSptFallbackDbMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sptFallbackDbDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SptFallbackDb in the database
        List<SptFallbackDb> sptFallbackDbList = sptFallbackDbRepository.findAll();
        assertThat(sptFallbackDbList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSptFallbackDbWithPatch() throws Exception {
        // Initialize the database
        sptFallbackDbRepository.saveAndFlush(sptFallbackDb);

        int databaseSizeBeforeUpdate = sptFallbackDbRepository.findAll().size();

        // Update the sptFallbackDb using partial update
        SptFallbackDb partialUpdatedSptFallbackDb = new SptFallbackDb();
        partialUpdatedSptFallbackDb.setId(sptFallbackDb.getId());

        partialUpdatedSptFallbackDb.name(UPDATED_NAME);

        restSptFallbackDbMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSptFallbackDb.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSptFallbackDb))
            )
            .andExpect(status().isOk());

        // Validate the SptFallbackDb in the database
        List<SptFallbackDb> sptFallbackDbList = sptFallbackDbRepository.findAll();
        assertThat(sptFallbackDbList).hasSize(databaseSizeBeforeUpdate);
        SptFallbackDb testSptFallbackDb = sptFallbackDbList.get(sptFallbackDbList.size() - 1);
        assertThat(testSptFallbackDb.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testSptFallbackDb.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSptFallbackDb.getDbid()).isEqualTo(DEFAULT_DBID);
    }

    @Test
    @Transactional
    void fullUpdateSptFallbackDbWithPatch() throws Exception {
        // Initialize the database
        sptFallbackDbRepository.saveAndFlush(sptFallbackDb);

        int databaseSizeBeforeUpdate = sptFallbackDbRepository.findAll().size();

        // Update the sptFallbackDb using partial update
        SptFallbackDb partialUpdatedSptFallbackDb = new SptFallbackDb();
        partialUpdatedSptFallbackDb.setId(sptFallbackDb.getId());

        partialUpdatedSptFallbackDb.version(UPDATED_VERSION).name(UPDATED_NAME).dbid(UPDATED_DBID);

        restSptFallbackDbMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSptFallbackDb.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSptFallbackDb))
            )
            .andExpect(status().isOk());

        // Validate the SptFallbackDb in the database
        List<SptFallbackDb> sptFallbackDbList = sptFallbackDbRepository.findAll();
        assertThat(sptFallbackDbList).hasSize(databaseSizeBeforeUpdate);
        SptFallbackDb testSptFallbackDb = sptFallbackDbList.get(sptFallbackDbList.size() - 1);
        assertThat(testSptFallbackDb.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testSptFallbackDb.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSptFallbackDb.getDbid()).isEqualTo(UPDATED_DBID);
    }

    @Test
    @Transactional
    void patchNonExistingSptFallbackDb() throws Exception {
        int databaseSizeBeforeUpdate = sptFallbackDbRepository.findAll().size();
        sptFallbackDb.setId(count.incrementAndGet());

        // Create the SptFallbackDb
        SptFallbackDbDTO sptFallbackDbDTO = sptFallbackDbMapper.toDto(sptFallbackDb);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSptFallbackDbMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sptFallbackDbDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sptFallbackDbDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SptFallbackDb in the database
        List<SptFallbackDb> sptFallbackDbList = sptFallbackDbRepository.findAll();
        assertThat(sptFallbackDbList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSptFallbackDb() throws Exception {
        int databaseSizeBeforeUpdate = sptFallbackDbRepository.findAll().size();
        sptFallbackDb.setId(count.incrementAndGet());

        // Create the SptFallbackDb
        SptFallbackDbDTO sptFallbackDbDTO = sptFallbackDbMapper.toDto(sptFallbackDb);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSptFallbackDbMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sptFallbackDbDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SptFallbackDb in the database
        List<SptFallbackDb> sptFallbackDbList = sptFallbackDbRepository.findAll();
        assertThat(sptFallbackDbList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSptFallbackDb() throws Exception {
        int databaseSizeBeforeUpdate = sptFallbackDbRepository.findAll().size();
        sptFallbackDb.setId(count.incrementAndGet());

        // Create the SptFallbackDb
        SptFallbackDbDTO sptFallbackDbDTO = sptFallbackDbMapper.toDto(sptFallbackDb);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSptFallbackDbMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sptFallbackDbDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SptFallbackDb in the database
        List<SptFallbackDb> sptFallbackDbList = sptFallbackDbRepository.findAll();
        assertThat(sptFallbackDbList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSptFallbackDb() throws Exception {
        // Initialize the database
        sptFallbackDbRepository.saveAndFlush(sptFallbackDb);

        int databaseSizeBeforeDelete = sptFallbackDbRepository.findAll().size();

        // Delete the sptFallbackDb
        restSptFallbackDbMockMvc
            .perform(delete(ENTITY_API_URL_ID, sptFallbackDb.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SptFallbackDb> sptFallbackDbList = sptFallbackDbRepository.findAll();
        assertThat(sptFallbackDbList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
