package com.rfb.service;

import com.rfb.service.dto.RfbLocationDTO;
import java.util.List;

/**
 * Service Interface for managing RfbLocation.
 */
public interface RfbLocationService {

    /**
     * Save a rfbLocation.
     *
     * @param rfbLocationDTO the entity to save
     * @return the persisted entity
     */
    RfbLocationDTO save(RfbLocationDTO rfbLocationDTO);

    /**
     * Get all the rfbLocations.
     *
     * @return the list of entities
     */
    List<RfbLocationDTO> findAll();

    /**
     * Get the "id" rfbLocation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RfbLocationDTO findOne(Long id);

    /**
     * Delete the "id" rfbLocation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
