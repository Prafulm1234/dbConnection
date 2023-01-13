package com.ezapi.api.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SptFallbackDbMapperTest {

    private SptFallbackDbMapper sptFallbackDbMapper;

    @BeforeEach
    public void setUp() {
        sptFallbackDbMapper = new SptFallbackDbMapperImpl();
    }
}
