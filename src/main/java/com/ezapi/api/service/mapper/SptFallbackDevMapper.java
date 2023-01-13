package com.ezapi.api.service.mapper;

import com.ezapi.api.domain.*;
import com.ezapi.api.service.dto.SptFallbackDevDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SptFallbackDev} and its DTO {@link SptFallbackDevDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SptFallbackDevMapper extends EntityMapper<SptFallbackDevDTO, SptFallbackDev> {}
