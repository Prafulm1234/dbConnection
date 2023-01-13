package com.ezapi.api.service.impl;

import com.ezapi.api.domain.SptFallbackDb;
import com.ezapi.api.repository.SptFallbackDbRepository;
import com.ezapi.api.service.SptFallbackDbService;
import com.ezapi.api.service.dto.SptFallbackDbDTO;
import com.ezapi.api.service.mapper.SptFallbackDbMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SptFallbackDb}.
 */
@Service
@Transactional
public class SptFallbackDbServiceImpl implements SptFallbackDbService {

    private final Logger log = LoggerFactory.getLogger(SptFallbackDbServiceImpl.class);

    private final SptFallbackDbRepository sptFallbackDbRepository;

    private final SptFallbackDbMapper sptFallbackDbMapper;

    public SptFallbackDbServiceImpl(SptFallbackDbRepository sptFallbackDbRepository, SptFallbackDbMapper sptFallbackDbMapper) {
        this.sptFallbackDbRepository = sptFallbackDbRepository;
        this.sptFallbackDbMapper = sptFallbackDbMapper;
    }

    @Override
    public SptFallbackDbDTO save(SptFallbackDbDTO sptFallbackDbDTO) {
        log.debug("Request to save SptFallbackDb : {}", sptFallbackDbDTO);
        SptFallbackDb sptFallbackDb = sptFallbackDbMapper.toEntity(sptFallbackDbDTO);
        sptFallbackDb = sptFallbackDbRepository.save(sptFallbackDb);
        return sptFallbackDbMapper.toDto(sptFallbackDb);
    }

    @Override
    public Optional<SptFallbackDbDTO> partialUpdate(SptFallbackDbDTO sptFallbackDbDTO) {
        log.debug("Request to partially update SptFallbackDb : {}", sptFallbackDbDTO);

        return sptFallbackDbRepository
            .findById(sptFallbackDbDTO.getId())
            .map(
                existingSptFallbackDb -> {
                    sptFallbackDbMapper.partialUpdate(existingSptFallbackDb, sptFallbackDbDTO);

                    return existingSptFallbackDb;
                }
            )
            .map(sptFallbackDbRepository::save)
            .map(sptFallbackDbMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SptFallbackDbDTO> findAll() {
        log.debug("Request to get all SptFallbackDbs");
        return sptFallbackDbRepository.findAll().stream().map(sptFallbackDbMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SptFallbackDbDTO> findOne(Long id) {
        log.debug("Request to get SptFallbackDb : {}", id);
        return sptFallbackDbRepository.findById(id).map(sptFallbackDbMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SptFallbackDb : {}", id);
        sptFallbackDbRepository.deleteById(id);
    }
}
