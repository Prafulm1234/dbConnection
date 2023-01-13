package com.ezapi.api.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ezapi.api.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SptFallbackDbTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SptFallbackDb.class);
        SptFallbackDb sptFallbackDb1 = new SptFallbackDb();
        sptFallbackDb1.setId(1L);
        SptFallbackDb sptFallbackDb2 = new SptFallbackDb();
        sptFallbackDb2.setId(sptFallbackDb1.getId());
        assertThat(sptFallbackDb1).isEqualTo(sptFallbackDb2);
        sptFallbackDb2.setId(2L);
        assertThat(sptFallbackDb1).isNotEqualTo(sptFallbackDb2);
        sptFallbackDb1.setId(null);
        assertThat(sptFallbackDb1).isNotEqualTo(sptFallbackDb2);
    }
}
