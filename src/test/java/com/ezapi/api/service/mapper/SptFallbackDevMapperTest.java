package com.ezapi.api.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SptFallbackDevMapperTest {

    private SptFallbackDevMapper sptFallbackDevMapper;

    @BeforeEach
    public void setUp() {
        sptFallbackDevMapper = new SptFallbackDevMapperImpl();
    }
}
