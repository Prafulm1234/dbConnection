package com.ezapi.api.web.rest;

import com.ezapi.api.repository.SptFallbackDevRepository;
import com.ezapi.api.service.SptFallbackDevService;
import com.ezapi.api.service.dto.SptFallbackDevDTO;
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
 * REST controller for managing {@link com.ezapi.api.domain.SptFallbackDev}.
 */
@RestController
@RequestMapping("/api")
public class SptFallbackDevResource {

    private final Logger log = LoggerFactory.getLogger(SptFallbackDevResource.class);

    private static final String ENTITY_NAME = "dbconnectionSptFallbackDev";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SptFallbackDevService sptFallbackDevService;

    private final SptFallbackDevRepository sptFallbackDevRepository;

    public SptFallbackDevResource(SptFallbackDevService sptFallbackDevService, SptFallbackDevRepository sptFallbackDevRepository) {
        this.sptFallbackDevService = sptFallbackDevService;
        this.sptFallbackDevRepository = sptFallbackDevRepository;
    }

    /**
     * {@code POST  /spt-fallback-devs} : Create a new sptFallbackDev.
     *
     * @param sptFallbackDevDTO the sptFallbackDevDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sptFallbackDevDTO, or with status {@code 400 (Bad Request)} if the sptFallbackDev has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/spt-fallback-devs")
    public ResponseEntity<SptFallbackDevDTO> createSptFallbackDev(@RequestBody SptFallbackDevDTO sptFallbackDevDTO)
        throws URISyntaxException {
        log.debug("REST request to save SptFallbackDev : {}", sptFallbackDevDTO);
        if (sptFallbackDevDTO.getId() != null) {
            throw new BadRequestAlertException("A new sptFallbackDev cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SptFallbackDevDTO result = sptFallbackDevService.save(sptFallbackDevDTO);
        return ResponseEntity
            .created(new URI("/api/spt-fallback-devs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /spt-fallback-devs/:id} : Updates an existing sptFallbackDev.
     *
     * @param id the id of the sptFallbackDevDTO to save.
     * @param sptFallbackDevDTO the sptFallbackDevDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sptFallbackDevDTO,
     * or with status {@code 400 (Bad Request)} if the sptFallbackDevDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sptFallbackDevDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/spt-fallback-devs/{id}")
    public ResponseEntity<SptFallbackDevDTO> updateSptFallbackDev(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SptFallbackDevDTO sptFallbackDevDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SptFallbackDev : {}, {}", id, sptFallbackDevDTO);
        if (sptFallbackDevDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sptFallbackDevDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sptFallbackDevRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SptFallbackDevDTO result = sptFallbackDevService.save(sptFallbackDevDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sptFallbackDevDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /spt-fallback-devs/:id} : Partial updates given fields of an existing sptFallbackDev, field will ignore if it is null
     *
     * @param id the id of the sptFallbackDevDTO to save.
     * @param sptFallbackDevDTO the sptFallbackDevDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sptFallbackDevDTO,
     * or with status {@code 400 (Bad Request)} if the sptFallbackDevDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sptFallbackDevDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sptFallbackDevDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/spt-fallback-devs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<SptFallbackDevDTO> partialUpdateSptFallbackDev(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SptFallbackDevDTO sptFallbackDevDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SptFallbackDev partially : {}, {}", id, sptFallbackDevDTO);
        if (sptFallbackDevDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sptFallbackDevDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sptFallbackDevRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SptFallbackDevDTO> result = sptFallbackDevService.partialUpdate(sptFallbackDevDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sptFallbackDevDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /spt-fallback-devs} : get all the sptFallbackDevs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sptFallbackDevs in body.
     */
    @GetMapping("/spt-fallback-devs")
    public List<SptFallbackDevDTO> getAllSptFallbackDevs() {
        log.debug("REST request to get all SptFallbackDevs");
        return sptFallbackDevService.findAll();
    }

    /**
     * {@code GET  /spt-fallback-devs/:id} : get the "id" sptFallbackDev.
     *
     * @param id the id of the sptFallbackDevDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sptFallbackDevDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/spt-fallback-devs/{id}")
    public ResponseEntity<SptFallbackDevDTO> getSptFallbackDev(@PathVariable Long id) {
        log.debug("REST request to get SptFallbackDev : {}", id);
        Optional<SptFallbackDevDTO> sptFallbackDevDTO = sptFallbackDevService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sptFallbackDevDTO);
    }

    /**
     * {@code DELETE  /spt-fallback-devs/:id} : delete the "id" sptFallbackDev.
     *
     * @param id the id of the sptFallbackDevDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/spt-fallback-devs/{id}")
    public ResponseEntity<Void> deleteSptFallbackDev(@PathVariable Long id) {
        log.debug("REST request to delete SptFallbackDev : {}", id);
        sptFallbackDevService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
