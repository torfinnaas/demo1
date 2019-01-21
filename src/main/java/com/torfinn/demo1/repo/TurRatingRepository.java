package com.torfinn.demo1.repo;

import com.torfinn.demo1.domain.TurRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Tour Rating Repository Interface
 *
 * Created by Mary Ellen Bowman
 */
@RepositoryRestResource(exported = false)
public interface TurRatingRepository extends JpaRepository<TurRating, Integer> {

    /**
     * Lookup all the TurRatings for a tour.
     *
     * @param turId is the tour Identifier
     * @return a List of any found TurRatings
     */
    List<TurRating> findByTurId(Integer turId);

    /**
     * Lookup a page of TurRatings for a tour.
     *
     * @param turId tourId is the tour Identifier
     * @param pageable details for the desired page
     * @return a Page of any found TurRatings
     */
    Page<TurRating> findByTurId(Integer turId, Pageable pageable);

    /**
     * Lookup a TurRating by the TourId and Customer Id
     * @param turId
     * @param customerId
     * @return TurRating if found, null otherwise.
     */
    Optional<TurRating> findByTurIdAndCustomerId(Integer turId, Integer customerId);
}