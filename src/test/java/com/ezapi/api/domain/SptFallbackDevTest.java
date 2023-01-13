package com.ezapi.api.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ezapi.api.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SptFallbackDevTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SptFallbackDev.class);
        SptFallbackDev sptFallbackDev1 = new SptFallbackDev();
        sptFallbackDev1.setId(1L);
        SptFallbackDev sptFallbackDev2 = new SptFallbackDev();
        sptFallbackDev2.setId(sptFallbackDev1.getId());
        assertThat(sptFallbackDev1).isEqualTo(sptFallbackDev2);
        sptFallbackDev2.setId(2L);
        assertThat(sptFallbackDev1).isNotEqualTo(sptFallbackDev2);
        sptFallbackDev1.setId(null);
        assertThat(sptFallbackDev1).isNotEqualTo(sptFallbackDev2);
    }
}
