package com.ezapi.api.repository;

import com.ezapi.api.domain.SptFallbackDb;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SptFallbackDb entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SptFallbackDbRepository extends JpaRepository<SptFallbackDb, Long> {}
