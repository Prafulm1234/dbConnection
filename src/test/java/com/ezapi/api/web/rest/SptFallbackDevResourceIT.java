package com.ezapi.api.web.rest;

import static com.ezapi.api.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ezapi.api.IntegrationTest;
import com.ezapi.api.domain.SptFallbackDev;
import com.ezapi.api.repository.SptFallbackDevRepository;
import com.ezapi.api.service.dto.SptFallbackDevDTO;
import com.ezapi.api.service.mapper.SptFallbackDevMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
 * Integration tests for the {@link SptFallbackDevResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SptFallbackDevResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_XSERVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_XSERVER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_XFALLBACK_DRIVE = "AAAAAAAAAA";
    private static final String UPDATED_XFALLBACK_DRIVE = "BBBBBBBBBB";

    private static final String DEFAULT_XFALLBACK_LOW = "AAAAAAAAAA";
    private static final String UPDATED_XFALLBACK_LOW = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_XDTTM_LAST_INS_UPD = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_XDTTM_LAST_INS_UPD = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LOW = "AAAAAAAAAA";
    private static final String UPDATED_LOW = "BBBBBBBBBB";

    private static final String DEFAULT_HIGH = "AAAAAAAAAA";
    private static final String UPDATED_HIGH = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_XDTTM_INS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_XDTTM_INS = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_PHYNAME = "AAAAAAAAAA";
    private static final String UPDATED_PHYNAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/spt-fallback-devs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SptFallbackDevRepository sptFallbackDevRepository;

    @Autowired
    private SptFallbackDevMapper sptFallbackDevMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSptFallbackDevMockMvc;

    private SptFallbackDev sptFallbackDev;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SptFallbackDev createEntity(EntityManager em) {
        SptFallbackDev sptFallbackDev = new SptFallbackDev()
            .name(DEFAULT_NAME)
            .xserverName(DEFAULT_XSERVER_NAME)
            .xfallbackDrive(DEFAULT_XFALLBACK_DRIVE)
            .xfallbackLow(DEFAULT_XFALLBACK_LOW)
            .xdttmLastInsUpd(DEFAULT_XDTTM_LAST_INS_UPD)
            .low(DEFAULT_LOW)
            .high(DEFAULT_HIGH)
            .status(DEFAULT_STATUS)
            .xdttmIns(DEFAULT_XDTTM_INS)
            .phyname(DEFAULT_PHYNAME);
        return sptFallbackDev;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SptFallbackDev createUpdatedEntity(EntityManager em) {
        SptFallbackDev sptFallbackDev = new SptFallbackDev()
            .name(UPDATED_NAME)
            .xserverName(UPDATED_XSERVER_NAME)
            .xfallbackDrive(UPDATED_XFALLBACK_DRIVE)
            .xfallbackLow(UPDATED_XFALLBACK_LOW)
            .xdttmLastInsUpd(UPDATED_XDTTM_LAST_INS_UPD)
            .low(UPDATED_LOW)
            .high(UPDATED_HIGH)
            .status(UPDATED_STATUS)
            .xdttmIns(UPDATED_XDTTM_INS)
            .phyname(UPDATED_PHYNAME);
        return sptFallbackDev;
    }

    @BeforeEach
    public void initTest() {
        sptFallbackDev = createEntity(em);
    }

    @Test
    @Transactional
    void createSptFallbackDev() throws Exception {
        int databaseSizeBeforeCreate = sptFallbackDevRepository.findAll().size();
        // Create the SptFallbackDev
        SptFallbackDevDTO sptFallbackDevDTO = sptFallbackDevMapper.toDto(sptFallbackDev);
        restSptFallbackDevMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sptFallbackDevDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SptFallbackDev in the database
        List<SptFallbackDev> sptFallbackDevList = sptFallbackDevRepository.findAll();
        assertThat(sptFallbackDevList).hasSize(databaseSizeBeforeCreate + 1);
        SptFallbackDev testSptFallbackDev = sptFallbackDevList.get(sptFallbackDevList.size() - 1);
        assertThat(testSptFallbackDev.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSptFallbackDev.getXserverName()).isEqualTo(DEFAULT_XSERVER_NAME);
        assertThat(testSptFallbackDev.getXfallbackDrive()).isEqualTo(DEFAULT_XFALLBACK_DRIVE);
        assertThat(testSptFallbackDev.getXfallbackLow()).isEqualTo(DEFAULT_XFALLBACK_LOW);
        assertThat(testSptFallbackDev.getXdttmLastInsUpd()).isEqualTo(DEFAULT_XDTTM_LAST_INS_UPD);
        assertThat(testSptFallbackDev.getLow()).isEqualTo(DEFAULT_LOW);
        assertThat(testSptFallbackDev.getHigh()).isEqualTo(DEFAULT_HIGH);
        assertThat(testSptFallbackDev.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSptFallbackDev.getXdttmIns()).isEqualTo(DEFAULT_XDTTM_INS);
        assertThat(testSptFallbackDev.getPhyname()).isEqualTo(DEFAULT_PHYNAME);
    }

    @Test
    @Transactional
    void createSptFallbackDevWithExistingId() throws Exception {
        // Create the SptFallbackDev with an existing ID
        sptFallbackDev.setId(1L);
        SptFallbackDevDTO sptFallbackDevDTO = sptFallbackDevMapper.toDto(sptFallbackDev);

        int databaseSizeBeforeCreate = sptFallbackDevRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSptFallbackDevMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sptFallbackDevDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SptFallbackDev in the database
        List<SptFallbackDev> sptFallbackDevList = sptFallbackDevRepository.findAll();
        assertThat(sptFallbackDevList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSptFallbackDevs() throws Exception {
        // Initialize the database
        sptFallbackDevRepository.saveAndFlush(sptFallbackDev);

        // Get all the sptFallbackDevList
        restSptFallbackDevMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sptFallbackDev.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].xserverName").value(hasItem(DEFAULT_XSERVER_NAME)))
            .andExpect(jsonPath("$.[*].xfallbackDrive").value(hasItem(DEFAULT_XFALLBACK_DRIVE)))
            .andExpect(jsonPath("$.[*].xfallbackLow").value(hasItem(DEFAULT_XFALLBACK_LOW)))
            .andExpect(jsonPath("$.[*].xdttmLastInsUpd").value(hasItem(sameInstant(DEFAULT_XDTTM_LAST_INS_UPD))))
            .andExpect(jsonPath("$.[*].low").value(hasItem(DEFAULT_LOW)))
            .andExpect(jsonPath("$.[*].high").value(hasItem(DEFAULT_HIGH)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].xdttmIns").value(hasItem(sameInstant(DEFAULT_XDTTM_INS))))
            .andExpect(jsonPath("$.[*].phyname").value(hasItem(DEFAULT_PHYNAME)));
    }

    @Test
    @Transactional
    void getSptFallbackDev() throws Exception {
        // Initialize the database
        sptFallbackDevRepository.saveAndFlush(sptFallbackDev);

        // Get the sptFallbackDev
        restSptFallbackDevMockMvc
            .perform(get(ENTITY_API_URL_ID, sptFallbackDev.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sptFallbackDev.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.xserverName").value(DEFAULT_XSERVER_NAME))
            .andExpect(jsonPath("$.xfallbackDrive").value(DEFAULT_XFALLBACK_DRIVE))
            .andExpect(jsonPath("$.xfallbackLow").value(DEFAULT_XFALLBACK_LOW))
            .andExpect(jsonPath("$.xdttmLastInsUpd").value(sameInstant(DEFAULT_XDTTM_LAST_INS_UPD)))
            .andExpect(jsonPath("$.low").value(DEFAULT_LOW))
            .andExpect(jsonPath("$.high").value(DEFAULT_HIGH))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.xdttmIns").value(sameInstant(DEFAULT_XDTTM_INS)))
            .andExpect(jsonPath("$.phyname").value(DEFAULT_PHYNAME));
    }

    @Test
    @Transactional
    void getNonExistingSptFallbackDev() throws Exception {
        // Get the sptFallbackDev
        restSptFallbackDevMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSptFallbackDev() throws Exception {
        // Initialize the database
        sptFallbackDevRepository.saveAndFlush(sptFallbackDev);

        int databaseSizeBeforeUpdate = sptFallbackDevRepository.findAll().size();

        // Update the sptFallbackDev
        SptFallbackDev updatedSptFallbackDev = sptFallbackDevRepository.findById(sptFallbackDev.getId()).get();
        // Disconnect from session so that the updates on updatedSptFallbackDev are not directly saved in db
        em.detach(updatedSptFallbackDev);
        updatedSptFallbackDev
            .name(UPDATED_NAME)
            .xserverName(UPDATED_XSERVER_NAME)
            .xfallbackDrive(UPDATED_XFALLBACK_DRIVE)
            .xfallbackLow(UPDATED_XFALLBACK_LOW)
            .xdttmLastInsUpd(UPDATED_XDTTM_LAST_INS_UPD)
            .low(UPDATED_LOW)
            .high(UPDATED_HIGH)
            .status(UPDATED_STATUS)
            .xdttmIns(UPDATED_XDTTM_INS)
            .phyname(UPDATED_PHYNAME);
        SptFallbackDevDTO sptFallbackDevDTO = sptFallbackDevMapper.toDto(updatedSptFallbackDev);

        restSptFallbackDevMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sptFallbackDevDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sptFallbackDevDTO))
            )
            .andExpect(status().isOk());

        // Validate the SptFallbackDev in the database
        List<SptFallbackDev> sptFallbackDevList = sptFallbackDevRepository.findAll();
        assertThat(sptFallbackDevList).hasSize(databaseSizeBeforeUpdate);
        SptFallbackDev testSptFallbackDev = sptFallbackDevList.get(sptFallbackDevList.size() - 1);
        assertThat(testSptFallbackDev.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSptFallbackDev.getXserverName()).isEqualTo(UPDATED_XSERVER_NAME);
        assertThat(testSptFallbackDev.getXfallbackDrive()).isEqualTo(UPDATED_XFALLBACK_DRIVE);
        assertThat(testSptFallbackDev.getXfallbackLow()).isEqualTo(UPDATED_XFALLBACK_LOW);
        assertThat(testSptFallbackDev.getXdttmLastInsUpd()).isEqualTo(UPDATED_XDTTM_LAST_INS_UPD);
        assertThat(testSptFallbackDev.getLow()).isEqualTo(UPDATED_LOW);
        assertThat(testSptFallbackDev.getHigh()).isEqualTo(UPDATED_HIGH);
        assertThat(testSptFallbackDev.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSptFallbackDev.getXdttmIns()).isEqualTo(UPDATED_XDTTM_INS);
        assertThat(testSptFallbackDev.getPhyname()).isEqualTo(UPDATED_PHYNAME);
    }

    @Test
    @Transactional
    void putNonExistingSptFallbackDev() throws Exception {
        int databaseSizeBeforeUpdate = sptFallbackDevRepository.findAll().size();
        sptFallbackDev.setId(count.incrementAndGet());

        // Create the SptFallbackDev
        SptFallbackDevDTO sptFallbackDevDTO = sptFallbackDevMapper.toDto(sptFallbackDev);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSptFallbackDevMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sptFallbackDevDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sptFallbackDevDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SptFallbackDev in the database
        List<SptFallbackDev> sptFallbackDevList = sptFallbackDevRepository.findAll();
        assertThat(sptFallbackDevList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSptFallbackDev() throws Exception {
        int databaseSizeBeforeUpdate = sptFallbackDevRepository.findAll().size();
        sptFallbackDev.setId(count.incrementAndGet());

        // Create the SptFallbackDev
        SptFallbackDevDTO sptFallbackDevDTO = sptFallbackDevMapper.toDto(sptFallbackDev);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSptFallbackDevMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sptFallbackDevDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SptFallbackDev in the database
        List<SptFallbackDev> sptFallbackDevList = sptFallbackDevRepository.findAll();
        assertThat(sptFallbackDevList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSptFallbackDev() throws Exception {
        int databaseSizeBeforeUpdate = sptFallbackDevRepository.findAll().size();
        sptFallbackDev.setId(count.incrementAndGet());

        // Create the SptFallbackDev
        SptFallbackDevDTO sptFallbackDevDTO = sptFallbackDevMapper.toDto(sptFallbackDev);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSptFallbackDevMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sptFallbackDevDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SptFallbackDev in the database
        List<SptFallbackDev> sptFallbackDevList = sptFallbackDevRepository.findAll();
        assertThat(sptFallbackDevList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSptFallbackDevWithPatch() throws Exception {
        // Initialize the database
        sptFallbackDevRepository.saveAndFlush(sptFallbackDev);

        int databaseSizeBeforeUpdate = sptFallbackDevRepository.findAll().size();

        // Update the sptFallbackDev using partial update
        SptFallbackDev partialUpdatedSptFallbackDev = new SptFallbackDev();
        partialUpdatedSptFallbackDev.setId(sptFallbackDev.getId());

        partialUpdatedSptFallbackDev.name(UPDATED_NAME).xdttmLastInsUpd(UPDATED_XDTTM_LAST_INS_UPD).status(UPDATED_STATUS);

        restSptFallbackDevMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSptFallbackDev.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSptFallbackDev))
            )
            .andExpect(status().isOk());

        // Validate the SptFallbackDev in the database
        List<SptFallbackDev> sptFallbackDevList = sptFallbackDevRepository.findAll();
        assertThat(sptFallbackDevList).hasSize(databaseSizeBeforeUpdate);
        SptFallbackDev testSptFallbackDev = sptFallbackDevList.get(sptFallbackDevList.size() - 1);
        assertThat(testSptFallbackDev.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSptFallbackDev.getXserverName()).isEqualTo(DEFAULT_XSERVER_NAME);
        assertThat(testSptFallbackDev.getXfallbackDrive()).isEqualTo(DEFAULT_XFALLBACK_DRIVE);
        assertThat(testSptFallbackDev.getXfallbackLow()).isEqualTo(DEFAULT_XFALLBACK_LOW);
        assertThat(testSptFallbackDev.getXdttmLastInsUpd()).isEqualTo(UPDATED_XDTTM_LAST_INS_UPD);
        assertThat(testSptFallbackDev.getLow()).isEqualTo(DEFAULT_LOW);
        assertThat(testSptFallbackDev.getHigh()).isEqualTo(DEFAULT_HIGH);
        assertThat(testSptFallbackDev.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSptFallbackDev.getXdttmIns()).isEqualTo(DEFAULT_XDTTM_INS);
        assertThat(testSptFallbackDev.getPhyname()).isEqualTo(DEFAULT_PHYNAME);
    }

    @Test
    @Transactional
    void fullUpdateSptFallbackDevWithPatch() throws Exception {
        // Initialize the database
        sptFallbackDevRepository.saveAndFlush(sptFallbackDev);

        int databaseSizeBeforeUpdate = sptFallbackDevRepository.findAll().size();

        // Update the sptFallbackDev using partial update
        SptFallbackDev partialUpdatedSptFallbackDev = new SptFallbackDev();
        partialUpdatedSptFallbackDev.setId(sptFallbackDev.getId());

        partialUpdatedSptFallbackDev
            .name(UPDATED_NAME)
            .xserverName(UPDATED_XSERVER_NAME)
            .xfallbackDrive(UPDATED_XFALLBACK_DRIVE)
            .xfallbackLow(UPDATED_XFALLBACK_LOW)
            .xdttmLastInsUpd(UPDATED_XDTTM_LAST_INS_UPD)
            .low(UPDATED_LOW)
            .high(UPDATED_HIGH)
            .status(UPDATED_STATUS)
            .xdttmIns(UPDATED_XDTTM_INS)
            .phyname(UPDATED_PHYNAME);

        restSptFallbackDevMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSptFallbackDev.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSptFallbackDev))
            )
            .andExpect(status().isOk());

        // Validate the SptFallbackDev in the database
        List<SptFallbackDev> sptFallbackDevList = sptFallbackDevRepository.findAll();
        assertThat(sptFallbackDevList).hasSize(databaseSizeBeforeUpdate);
        SptFallbackDev testSptFallbackDev = sptFallbackDevList.get(sptFallbackDevList.size() - 1);
        assertThat(testSptFallbackDev.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSptFallbackDev.getXserverName()).isEqualTo(UPDATED_XSERVER_NAME);
        assertThat(testSptFallbackDev.getXfallbackDrive()).isEqualTo(UPDATED_XFALLBACK_DRIVE);
        assertThat(testSptFallbackDev.getXfallbackLow()).isEqualTo(UPDATED_XFALLBACK_LOW);
        assertThat(testSptFallbackDev.getXdttmLastInsUpd()).isEqualTo(UPDATED_XDTTM_LAST_INS_UPD);
        assertThat(testSptFallbackDev.getLow()).isEqualTo(UPDATED_LOW);
        assertThat(testSptFallbackDev.getHigh()).isEqualTo(UPDATED_HIGH);
        assertThat(testSptFallbackDev.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSptFallbackDev.getXdttmIns()).isEqualTo(UPDATED_XDTTM_INS);
        assertThat(testSptFallbackDev.getPhyname()).isEqualTo(UPDATED_PHYNAME);
    }

    @Test
    @Transactional
    void patchNonExistingSptFallbackDev() throws Exception {
        int databaseSizeBeforeUpdate = sptFallbackDevRepository.findAll().size();
        sptFallbackDev.setId(count.incrementAndGet());

        // Create the SptFallbackDev
        SptFallbackDevDTO sptFallbackDevDTO = sptFallbackDevMapper.toDto(sptFallbackDev);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSptFallbackDevMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sptFallbackDevDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sptFallbackDevDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SptFallbackDev in the database
        List<SptFallbackDev> sptFallbackDevList = sptFallbackDevRepository.findAll();
        assertThat(sptFallbackDevList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSptFallbackDev() throws Exception {
        int databaseSizeBeforeUpdate = sptFallbackDevRepository.findAll().size();
        sptFallbackDev.setId(count.incrementAndGet());

        // Create the SptFallbackDev
        SptFallbackDevDTO sptFallbackDevDTO = sptFallbackDevMapper.toDto(sptFallbackDev);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSptFallbackDevMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sptFallbackDevDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SptFallbackDev in the database
        List<SptFallbackDev> sptFallbackDevList = sptFallbackDevRepository.findAll();
        assertThat(sptFallbackDevList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSptFallbackDev() throws Exception {
        int databaseSizeBeforeUpdate = sptFallbackDevRepository.findAll().size();
        sptFallbackDev.setId(count.incrementAndGet());

        // Create the SptFallbackDev
        SptFallbackDevDTO sptFallbackDevDTO = sptFallbackDevMapper.toDto(sptFallbackDev);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSptFallbackDevMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sptFallbackDevDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SptFallbackDev in the database
        List<SptFallbackDev> sptFallbackDevList = sptFallbackDevRepository.findAll();
        assertThat(sptFallbackDevList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSptFallbackDev() throws Exception {
        // Initialize the database
        sptFallbackDevRepository.saveAndFlush(sptFallbackDev);

        int databaseSizeBeforeDelete = sptFallbackDevRepository.findAll().size();

        // Delete the sptFallbackDev
        restSptFallbackDevMockMvc
            .perform(delete(ENTITY_API_URL_ID, sptFallbackDev.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SptFallbackDev> sptFallbackDevList = sptFallbackDevRepository.findAll();
        assertThat(sptFallbackDevList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
