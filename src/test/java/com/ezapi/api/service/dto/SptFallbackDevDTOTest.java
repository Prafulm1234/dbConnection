package com.ezapi.api.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ezapi.api.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SptFallbackDevDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SptFallbackDevDTO.class);
        SptFallbackDevDTO sptFallbackDevDTO1 = new SptFallbackDevDTO();
        sptFallbackDevDTO1.setId(1L);
        SptFallbackDevDTO sptFallbackDevDTO2 = new SptFallbackDevDTO();
        assertThat(sptFallbackDevDTO1).isNotEqualTo(sptFallbackDevDTO2);
        sptFallbackDevDTO2.setId(sptFallbackDevDTO1.getId());
        assertThat(sptFallbackDevDTO1).isEqualTo(sptFallbackDevDTO2);
        sptFallbackDevDTO2.setId(2L);
        assertThat(sptFallbackDevDTO1).isNotEqualTo(sptFallbackDevDTO2);
        sptFallbackDevDTO1.setId(null);
        assertThat(sptFallbackDevDTO1).isNotEqualTo(sptFallbackDevDTO2);
    }
}
