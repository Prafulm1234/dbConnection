package com.ezapi.api.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ezapi.api.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SptFallbackDbDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SptFallbackDbDTO.class);
        SptFallbackDbDTO sptFallbackDbDTO1 = new SptFallbackDbDTO();
        sptFallbackDbDTO1.setId(1L);
        SptFallbackDbDTO sptFallbackDbDTO2 = new SptFallbackDbDTO();
        assertThat(sptFallbackDbDTO1).isNotEqualTo(sptFallbackDbDTO2);
        sptFallbackDbDTO2.setId(sptFallbackDbDTO1.getId());
        assertThat(sptFallbackDbDTO1).isEqualTo(sptFallbackDbDTO2);
        sptFallbackDbDTO2.setId(2L);
        assertThat(sptFallbackDbDTO1).isNotEqualTo(sptFallbackDbDTO2);
        sptFallbackDbDTO1.setId(null);
        assertThat(sptFallbackDbDTO1).isNotEqualTo(sptFallbackDbDTO2);
    }
}
