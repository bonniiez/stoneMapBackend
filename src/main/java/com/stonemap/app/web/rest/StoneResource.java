package com.stonemap.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.stonemap.app.domain.Stone;
import com.stonemap.app.domain.StoneLocation;
import com.stonemap.app.repository.StoneLocationRepository;
import com.stonemap.app.repository.StoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing users.
 * <p>
 * This class accesses the User entity, and needs to fetch its collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between User and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities, because people will
 * quite often do relationships with the user, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this case.
 */
@RestController
@RequestMapping("/api")
public class StoneResource {
    private final Logger log = LoggerFactory.getLogger(StoneResource.class);

    private final StoneRepository stoneRepository;
    private final StoneLocationRepository stoneLocationRepository;

    public StoneResource(StoneRepository stoneRepository, StoneLocationRepository stoneLocationRepository) {

        this.stoneRepository = stoneRepository;
        this.stoneLocationRepository = stoneLocationRepository;
    }


    /**
     * POST /stones : creates a new stone
     * @param stone the stone to create
     * @return the ResponseEntity with status 200 (Created) and with body of the new stone
     * @throws URISyntaxException
     */
    @PostMapping("/stones")
    public ResponseEntity<Stone> createUser(@Valid @RequestBody Stone stone) throws URISyntaxException {
        log.debug("REST request to save User : {}", stone);
        Stone stoneToSave = new Stone();
        stoneToSave.setName(stone.getName());
        stoneToSave.setUser(stone.getUser());
        stoneRepository.save(stoneToSave);

        return ResponseEntity.ok(stoneToSave);
    }


    @GetMapping("/stones/{stoneName}")
    @Timed
    public ResponseEntity<List<StoneLocation>> getStone(@PathVariable String stoneName) {
        log.debug("REST request to get Offer : {}", stoneName);
        Stone stone = stoneRepository.findStoneByName(stoneName);
        List<StoneLocation> stoneLocations = stoneLocationRepository.findAllByStone(stone);
        return ResponseEntity.ok(stoneLocations);
    }



}
