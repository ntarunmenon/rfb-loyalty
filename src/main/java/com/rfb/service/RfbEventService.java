package com.rfb.service;

import com.rfb.service.dto.RfbEventDTO;
import java.util.List;

/**
 * Service Interface for managing RfbEvent.
 */
public interface RfbEventService {

    /**
     * Save a rfbEvent.
     *
     * @param rfbEventDTO the entity to save
     * @return the persisted entity
     */
    RfbEventDTO save(RfbEventDTO rfbEventDTO);

    /**
     * Get all the rfbEvents.
     *
     * @return the list of entities
     */
    List<RfbEventDTO> findAll();

    /**
     * Get the "id" rfbEvent.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RfbEventDTO findOne(Long id);

    /**
     * Delete the "id" rfbEvent.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    RfbEventDTO findByTodayAndLocation(Long locationID);
}
