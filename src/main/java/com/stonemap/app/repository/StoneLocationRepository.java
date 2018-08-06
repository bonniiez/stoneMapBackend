package com.stonemap.app.repository;

import com.stonemap.app.domain.Stone;
import com.stonemap.app.domain.StoneLocation;
import com.stonemap.app.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface StoneLocationRepository extends JpaRepository<StoneLocation, Long> {

    List<StoneLocation> findAllByStone (Stone stone);

}


