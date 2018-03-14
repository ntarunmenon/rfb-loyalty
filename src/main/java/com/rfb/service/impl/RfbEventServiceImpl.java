package com.rfb.service.impl;

import com.rfb.domain.RfbEvent;
import com.rfb.domain.RfbLocation;
import com.rfb.repository.RfbEventRepository;
import com.rfb.repository.RfbLocationRepository;
import com.rfb.service.RfbEventService;
import com.rfb.service.dto.RfbEventDTO;
import com.rfb.service.mapper.RfbEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing RfbEvent.
 */
@Service
@Transactional
public class RfbEventServiceImpl implements RfbEventService {

    private final Logger log = LoggerFactory.getLogger(RfbEventServiceImpl.class);

    private final RfbEventRepository rfbEventRepository;

    private final RfbEventMapper rfbEventMapper;

    private final RfbLocationRepository locationRepository;

    public RfbEventServiceImpl(RfbEventRepository rfbEventRepository, RfbEventMapper rfbEventMapper,RfbLocationRepository locationRepository) {
        this.rfbEventRepository = rfbEventRepository;
        this.rfbEventMapper = rfbEventMapper;
        this.locationRepository = locationRepository;
    }

    /**
     * Save a rfbEvent.
     *
     * @param rfbEventDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RfbEventDTO save(RfbEventDTO rfbEventDTO) {
        log.debug("Request to save RfbEvent : {}", rfbEventDTO);
        RfbEvent rfbEvent = rfbEventMapper.toEntity(rfbEventDTO);
        rfbEvent = rfbEventRepository.save(rfbEvent);
        return rfbEventMapper.toDto(rfbEvent);
    }

    /**
     * Get all the rfbEvents.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RfbEventDTO> findAll() {
        log.debug("Request to get all RfbEvents");
        return rfbEventRepository.findAll().stream()
            .map(rfbEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one rfbEvent by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RfbEventDTO findOne(Long id) {
        log.debug("Request to get RfbEvent : {}", id);
        RfbEvent rfbEvent = rfbEventRepository.findOne(id);
        return rfbEventMapper.toDto(rfbEvent);
    }

    /**
     * Delete the rfbEvent by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RfbEvent : {}", id);
        rfbEventRepository.delete(id);
    }

    @Override
    public RfbEventDTO findByTodayAndLocation(Long locationID) {
        RfbLocation location = locationRepository.findOne(locationID);
        RfbEvent rfbEvent = rfbEventRepository.findByEventDateEqualsAndRfbLocationEquals(LocalDate.now(),location);
        return rfbEventMapper.toDto(rfbEvent);
    }


}
