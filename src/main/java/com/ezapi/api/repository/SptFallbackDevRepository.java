package com.ezapi.api.repository;

import com.ezapi.api.domain.SptFallbackDev;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SptFallbackDev entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SptFallbackDevRepository extends JpaRepository<SptFallbackDev, Long> {}
