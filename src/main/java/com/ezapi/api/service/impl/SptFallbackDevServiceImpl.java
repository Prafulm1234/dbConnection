package com.ezapi.api.service.impl;

import com.ezapi.api.domain.SptFallbackDev;
import com.ezapi.api.repository.SptFallbackDevRepository;
import com.ezapi.api.service.SptFallbackDevService;
import com.ezapi.api.service.dto.SptFallbackDevDTO;
import com.ezapi.api.service.mapper.SptFallbackDevMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SptFallbackDev}.
 */
@Service
@Transactional
public class SptFallbackDevServiceImpl implements SptFallbackDevService {

    private final Logger log = LoggerFactory.getLogger(SptFallbackDevServiceImpl.class);

    private final SptFallbackDevRepository sptFallbackDevRepository;

    private final SptFallbackDevMapper sptFallbackDevMapper;

    public SptFallbackDevServiceImpl(SptFallbackDevRepository sptFallbackDevRepository, SptFallbackDevMapper sptFallbackDevMapper) {
        this.sptFallbackDevRepository = sptFallbackDevRepository;
        this.sptFallbackDevMapper = sptFallbackDevMapper;
    }

    @Override
    public SptFallbackDevDTO save(SptFallbackDevDTO sptFallbackDevDTO) {
        log.debug("Request to save SptFallbackDev : {}", sptFallbackDevDTO);
        SptFallbackDev sptFallbackDev = sptFallbackDevMapper.toEntity(sptFallbackDevDTO);
        sptFallbackDev = sptFallbackDevRepository.save(sptFallbackDev);
        return sptFallbackDevMapper.toDto(sptFallbackDev);
    }

    @Override
    public Optional<SptFallbackDevDTO> partialUpdate(SptFallbackDevDTO sptFallbackDevDTO) {
        log.debug("Request to partially update SptFallbackDev : {}", sptFallbackDevDTO);

        return sptFallbackDevRepository
            .findById(sptFallbackDevDTO.getId())
            .map(
                existingSptFallbackDev -> {
                    sptFallbackDevMapper.partialUpdate(existingSptFallbackDev, sptFallbackDevDTO);

                    return existingSptFallbackDev;
                }
            )
            .map(sptFallbackDevRepository::save)
            .map(sptFallbackDevMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SptFallbackDevDTO> findAll() {
        log.debug("Request to get all SptFallbackDevs");
        return sptFallbackDevRepository
            .findAll()
            .stream()
            .map(sptFallbackDevMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SptFallbackDevDTO> findOne(Long id) {
        log.debug("Request to get SptFallbackDev : {}", id);
        return sptFallbackDevRepository.findById(id).map(sptFallbackDevMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SptFallbackDev : {}", id);
        sptFallbackDevRepository.deleteById(id);
    }
}
