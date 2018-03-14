package com.rfb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.rfb.service.RfbEventService;
import com.rfb.service.dto.RfbEventDTO;
import com.rfb.web.rest.errors.BadRequestAlertException;
import com.rfb.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RfbEvent.
 */
@RestController
@RequestMapping("/api")
public class RfbEventResource {

    private final Logger log = LoggerFactory.getLogger(RfbEventResource.class);

    private static final String ENTITY_NAME = "rfbEvent";

    private final RfbEventService rfbEventService;

    public RfbEventResource(RfbEventService rfbEventService) {
        this.rfbEventService = rfbEventService;
    }

    /**
     * POST  /rfb-events : Create a new rfbEvent.
     *
     * @param rfbEventDTO the rfbEventDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rfbEventDTO, or with status 400 (Bad Request) if the rfbEvent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rfb-events")
    @Timed
    public ResponseEntity<RfbEventDTO> createRfbEvent(@RequestBody RfbEventDTO rfbEventDTO) throws URISyntaxException {
        log.debug("REST request to save RfbEvent : {}", rfbEventDTO);
        if (rfbEventDTO.getId() != null) {
            throw new BadRequestAlertException("A new rfbEvent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RfbEventDTO result = rfbEventService.save(rfbEventDTO);
        return ResponseEntity.created(new URI("/api/rfb-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rfb-events : Updates an existing rfbEvent.
     *
     * @param rfbEventDTO the rfbEventDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rfbEventDTO,
     * or with status 400 (Bad Request) if the rfbEventDTO is not valid,
     * or with status 500 (Internal Server Error) if the rfbEventDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rfb-events")
    @Timed
    public ResponseEntity<RfbEventDTO> updateRfbEvent(@RequestBody RfbEventDTO rfbEventDTO) throws URISyntaxException {
        log.debug("REST request to update RfbEvent : {}", rfbEventDTO);
        if (rfbEventDTO.getId() == null) {
            return createRfbEvent(rfbEventDTO);
        }
        RfbEventDTO result = rfbEventService.save(rfbEventDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rfbEventDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rfb-events : get all the rfbEvents.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rfbEvents in body
     */
    @GetMapping("/rfb-events")
    @Timed
    public List<RfbEventDTO> getAllRfbEvents() {
        log.debug("REST request to get all RfbEvents");
        return rfbEventService.findAll();
        }

    /**
     * GET  /rfb-events/:id : get the "id" rfbEvent.
     *
     * @param id the id of the rfbEventDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rfbEventDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rfb-events/{id}")
    @Timed
    public ResponseEntity<RfbEventDTO> getRfbEvent(@PathVariable Long id) {
        log.debug("REST request to get RfbEvent : {}", id);
        RfbEventDTO rfbEventDTO = rfbEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(rfbEventDTO));
    }

    /**
     * DELETE  /rfb-events/:id : delete the "id" rfbEvent.
     *
     * @param id the id of the rfbEventDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rfb-events/{id}")
    @Timed
    public ResponseEntity<Void> deleteRfbEvent(@PathVariable Long id) {
        log.debug("REST request to delete RfbEvent : {}", id);
        rfbEventService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /rfb-events/location/id : get a single event for the location and today
     *
     * @param locationID the location of the event
     * @return the ResponseEntity with status 200 (OK) and the list of rfbEvents in body
     */
    @GetMapping("/rfb-events/location/{locationID}")
    @Timed
    public ResponseEntity<RfbEventDTO> getTodaysEventByLocation(@PathVariable Long locationID) {
        log.debug("REST request to get a single event by location and today's date.");
        RfbEventDTO event = rfbEventService.findByTodayAndLocation(locationID);
        return new ResponseEntity<RfbEventDTO>(event,null, HttpStatus.OK);
    }

}
