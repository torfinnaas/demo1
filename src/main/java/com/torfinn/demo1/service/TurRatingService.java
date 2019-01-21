package com.torfinn.demo1.service;

import com.torfinn.demo1.domain.Tur;
import com.torfinn.demo1.domain.TurRating;
import com.torfinn.demo1.repo.TurRatingRepository;
import com.torfinn.demo1.repo.TurRepo;
import com.torfinn.demo1.web.TurRatingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Tour Rating Service
 *
 * Created by Mary Ellen Bowman.
 */
@Service
@Transactional
public class TurRatingService {
    private TurRatingRepository turRatingRepository;
    private TurRepo turRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(TurRatingController.class);

    /**
     * Construct TourRatingService
     *
     * @param turRatingRepository Tour Rating Repository
     * @param turRepo Tour Repository
     */
    @Autowired
    public TurRatingService(TurRatingRepository turRatingRepository, TurRepo turRepo) {
        this.turRatingRepository = turRatingRepository;
        this.turRepo = turRepo;
    }

    /**
     * Create a new Tour Rating in the database
     *
     * @param turId tour identifier
     * @param customerId customer identifier
     * @param score score of the tour rating
     * @param comment additional comment
     * @throws NoSuchElementException if no Tour found.
     */
    public void createNew(int turId, Integer customerId, Integer score, String comment) throws NoSuchElementException {
        turRatingRepository.save(new TurRating(verifyTur(turId), customerId, score, comment));    //    new TurRating(new TurRatingPk(turId, customerId), score, comment);
    }


    /**
     * Get a ratings by id.
     *
     * @param id rating identifier
     * @return TourRatings
     */
    public Optional<TurRating> lookupRatingById(int id)  {
        return turRatingRepository.findById(id);
    }



    /**
     * Get All Ratings.
     *
     * @return List of TourRatings
     */
    public List<TurRating> lookupAll()  {
        LOGGER.info("Lookup all Ratings");
        return turRatingRepository.findAll();
    }




    /**
     * Get a page of tour ratings for a tour.
     *
     * @param turId tour identifier
     * @param pageable page parameters to determine which elements to fetch
     * @return Page of TourRatings
     * @throws NoSuchElementException if no Tour found.
     */
    public Page<TurRating> lookupRatings(int turId, Pageable pageable) throws NoSuchElementException  {
        return turRatingRepository.findByTurId(verifyTur(turId).getId(), pageable);
    }

    /**
     * Update some of the elements of a Tour Rating.
     *
     * @param turId tour identifier
     * @param score score of the tour rating
     * @param comment additional comment
     * @return Tour Rating Domain Object
     * @throws NoSuchElementException if no Tour found.
     */
    public TurRating update(int turId, Integer customerId, Integer score, String comment) throws NoSuchElementException {
        TurRating rating = verifyTurRating(turId, customerId);
        rating.setScore(score);
        rating.setComment(comment);
        return turRatingRepository.save(rating);
    }

    /**
     * Update all of the elements of a Tour Rating.
     *
     * @param turId tour identifier
     * @param customerId customer identifier
     * @param score score of the tour rating
     * @param comment additional comment
     * @return Tour Rating Domain Object
     * @throws NoSuchElementException if no Tour found.
     */
    public TurRating updateSome(int turId, Integer customerId, Integer score, String comment)
            throws NoSuchElementException {

        TurRating rating = verifyTurRating(turId, customerId);
        if (score != null) {
            rating.setScore(score);
        }
        if (comment!= null) {
            rating.setComment(comment);
        }
        return turRatingRepository.save(rating);
    }

    /**
     * Delete a Tour Rating.
     *
     * @param turId tour identifier
     * @param customerId customer identifier
     * @throws NoSuchElementException if no Tour found.
     */
    public void delete(int turId, Integer customerId) throws NoSuchElementException {
        TurRating rating = verifyTurRating(turId, customerId);
        turRatingRepository.delete(rating);
    }
    /**
     * Get the average score of a tour.
     *
     * @param turId tour identifier
     * @return average score as a Double.
     * @throws NoSuchElementException
     */
    public Double getAverageScore(int turId)  throws NoSuchElementException  {
        List<TurRating> ratings = turRatingRepository.findByTurId(verifyTur(turId).getId());
        OptionalDouble average = ratings.stream().mapToInt((rating) -> rating.getScore()).average();
        return average.isPresent() ? average.getAsDouble():null;
    }
    /**
     * Service for many customers to give the same score for a service
     *
     * @param turId
     * @param score
     * @param customers
     */
     public void rateMany(int turId,  int score, Integer [] customers) {
        LOGGER.info("Setter rating på tur {} fra kunder {}", turId, Arrays.asList(customers).toString());
        turRepo.findById(turId).ifPresent(tur -> {
            for (Integer c : customers) {
                LOGGER.debug("Prøver å lage en tur-rating for kunde {}", c);
                turRatingRepository.save(new TurRating(tur, c, score));
            }
        });
    }

    /**
     * Verify and return the Tour given a tourId.
     *
     * @param turId
     * @return the found Tour
     * @throws NoSuchElementException if no Tour found.
     */
    private Tur verifyTur(int turId) throws NoSuchElementException {
        return turRepo.findById(turId).orElseThrow(() ->
                new NoSuchElementException("Tur eksisterer ikke " + turId)
        );
    }


    /**
     * Verify and return the TourRating for a particular tourId and Customer
     * @param turId
     * @param customerId
     * @return the found TourRating
     * @throws NoSuchElementException if no TourRating found
     */
    public TurRating verifyTurRating(int turId, int customerId) throws NoSuchElementException {
        return turRatingRepository.findByTurIdAndCustomerId(turId, customerId).orElseThrow(() ->
                new NoSuchElementException("Tour-Rating pair for request("
                        + turId + " for customer" + customerId));
    }


}
