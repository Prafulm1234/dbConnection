package com.ezapi.api.service.mapper;

import com.ezapi.api.domain.*;
import com.ezapi.api.service.dto.SptFallbackDbDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SptFallbackDb} and its DTO {@link SptFallbackDbDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SptFallbackDbMapper extends EntityMapper<SptFallbackDbDTO, SptFallbackDb> {}
