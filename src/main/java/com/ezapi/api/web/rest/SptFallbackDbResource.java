package com.ezapi.api.web.rest;

import com.ezapi.api.repository.SptFallbackDbRepository;
import com.ezapi.api.service.SptFallbackDbService;
import com.ezapi.api.service.dto.SptFallbackDbDTO;
import com.ezapi.api.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ezapi.api.domain.SptFallbackDb}.
 */
@RestController
@RequestMapping("/api")
public class SptFallbackDbResource {

    private final Logger log = LoggerFactory.getLogger(SptFallbackDbResource.class);

    private static final String ENTITY_NAME = "dbconnectionSptFallbackDb";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SptFallbackDbService sptFallbackDbService;

    private final SptFallbackDbRepository sptFallbackDbRepository;

    public SptFallbackDbResource(SptFallbackDbService sptFallbackDbService, SptFallbackDbRepository sptFallbackDbRepository) {
        this.sptFallbackDbService = sptFallbackDbService;
        this.sptFallbackDbRepository = sptFallbackDbRepository;
    }

    /**
     * {@code POST  /spt-fallback-dbs} : Create a new sptFallbackDb.
     *
     * @param sptFallbackDbDTO the sptFallbackDbDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sptFallbackDbDTO, or with status {@code 400 (Bad Request)} if the sptFallbackDb has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/spt-fallback-dbs")
    public ResponseEntity<SptFallbackDbDTO> createSptFallbackDb(@RequestBody SptFallbackDbDTO sptFallbackDbDTO) throws URISyntaxException {
        log.debug("REST request to save SptFallbackDb : {}", sptFallbackDbDTO);
        if (sptFallbackDbDTO.getId() != null) {
            throw new BadRequestAlertException("A new sptFallbackDb cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SptFallbackDbDTO result = sptFallbackDbService.save(sptFallbackDbDTO);
        return ResponseEntity
            .created(new URI("/api/spt-fallback-dbs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /spt-fallback-dbs/:id} : Updates an existing sptFallbackDb.
     *
     * @param id the id of the sptFallbackDbDTO to save.
     * @param sptFallbackDbDTO the sptFallbackDbDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sptFallbackDbDTO,
     * or with status {@code 400 (Bad Request)} if the sptFallbackDbDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sptFallbackDbDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/spt-fallback-dbs/{id}")
    public ResponseEntity<SptFallbackDbDTO> updateSptFallbackDb(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SptFallbackDbDTO sptFallbackDbDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SptFallbackDb : {}, {}", id, sptFallbackDbDTO);
        if (sptFallbackDbDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sptFallbackDbDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sptFallbackDbRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SptFallbackDbDTO result = sptFallbackDbService.save(sptFallbackDbDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sptFallbackDbDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /spt-fallback-dbs/:id} : Partial updates given fields of an existing sptFallbackDb, field will ignore if it is null
     *
     * @param id the id of the sptFallbackDbDTO to save.
     * @param sptFallbackDbDTO the sptFallbackDbDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sptFallbackDbDTO,
     * or with status {@code 400 (Bad Request)} if the sptFallbackDbDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sptFallbackDbDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sptFallbackDbDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/spt-fallback-dbs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<SptFallbackDbDTO> partialUpdateSptFallbackDb(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SptFallbackDbDTO sptFallbackDbDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SptFallbackDb partially : {}, {}", id, sptFallbackDbDTO);
        if (sptFallbackDbDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sptFallbackDbDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sptFallbackDbRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SptFallbackDbDTO> result = sptFallbackDbService.partialUpdate(sptFallbackDbDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sptFallbackDbDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /spt-fallback-dbs} : get all the sptFallbackDbs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sptFallbackDbs in body.
     */
    @GetMapping("/spt-fallback-dbs")
    public List<SptFallbackDbDTO> getAllSptFallbackDbs() {
        log.debug("REST request to get all SptFallbackDbs");
        return sptFallbackDbService.findAll();
    }

    /**
     * {@code GET  /spt-fallback-dbs/:id} : get the "id" sptFallbackDb.
     *
     * @param id the id of the sptFallbackDbDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sptFallbackDbDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/spt-fallback-dbs/{id}")
    public ResponseEntity<SptFallbackDbDTO> getSptFallbackDb(@PathVariable Long id) {
        log.debug("REST request to get SptFallbackDb : {}", id);
        Optional<SptFallbackDbDTO> sptFallbackDbDTO = sptFallbackDbService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sptFallbackDbDTO);
    }

    /**
     * {@code DELETE  /spt-fallback-dbs/:id} : delete the "id" sptFallbackDb.
     *
     * @param id the id of the sptFallbackDbDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/spt-fallback-dbs/{id}")
    public ResponseEntity<Void> deleteSptFallbackDb(@PathVariable Long id) {
        log.debug("REST request to delete SptFallbackDb : {}", id);
        sptFallbackDbService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
