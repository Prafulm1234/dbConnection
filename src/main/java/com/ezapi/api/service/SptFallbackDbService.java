package com.ezapi.api.service;

import com.ezapi.api.service.dto.SptFallbackDbDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ezapi.api.domain.SptFallbackDb}.
 */
public interface SptFallbackDbService {
    /**
     * Save a sptFallbackDb.
     *
     * @param sptFallbackDbDTO the entity to save.
     * @return the persisted entity.
     */
    SptFallbackDbDTO save(SptFallbackDbDTO sptFallbackDbDTO);

    /**
     * Partially updates a sptFallbackDb.
     *
     * @param sptFallbackDbDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SptFallbackDbDTO> partialUpdate(SptFallbackDbDTO sptFallbackDbDTO);

    /**
     * Get all the sptFallbackDbs.
     *
     * @return the list of entities.
     */
    List<SptFallbackDbDTO> findAll();

    /**
     * Get the "id" sptFallbackDb.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SptFallbackDbDTO> findOne(Long id);

    /**
     * Delete the "id" sptFallbackDb.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
