package com.torfinn.demo1.repo;

import com.torfinn.demo1.domain.TurRating;
import com.torfinn.demo1.domain.TurRatingPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Tour Rating Repository Interface
 *
 * Created by Mary Ellen Bowman
 */
@RepositoryRestResource(exported = false)
public interface TurRatingRepository extends CrudRepository<TurRating, TurRatingPk> {

    /**
     * Lookup all the TurRatings for a tour.
     *
     * @param turId is the tour Identifier
     * @return a List of any found TurRatings
     */
    List<TurRating> findByPkTurId(Integer turId);

    /**
     * Lookup a page of TurRatings for a tour.
     *
     * @param turId tourId is the tour Identifier
     * @param pageable details for the desired page
     * @return a Page of any found TurRatings
     */
    Page<TurRating> findByPkTurId(Integer turId, Pageable pageable);

    /**
     * Lookup a TurRating by the TourId and Customer Id
     * @param turId
     * @param customerId
     * @return TurRating if found, null otherwise.
     */
    TurRating findByPkTurIdAndPkCustomerId(Integer turId, Integer customerId);
}