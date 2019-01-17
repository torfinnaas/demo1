package com.torfinn.demo1.web;


import com.torfinn.demo1.domain.Tur;
import com.torfinn.demo1.domain.TurRating;
import com.torfinn.demo1.domain.TurRatingPk;
import com.torfinn.demo1.repo.TurRatingRepository;
import com.torfinn.demo1.repo.TurRepo;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;




/**
 * Tur Rating Controller
 */
@RestController
@RequestMapping(path = "/turer/{turId}/ratings")
public class TurRatingController {
    TurRatingRepository turRatingRepository;
    TurRepo turRepo;

    @Autowired
    public TurRatingController(TurRatingRepository turRatingRepository, TurRepo turRepo) {
        this.turRatingRepository = turRatingRepository;
        this.turRepo = turRepo;
    }

    protected TurRatingController() {

    }




    /**
     * Create a Tur Rating.
     *
     * @param turId
     * @param ratingDto
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createTurRating(@PathVariable(value = "turId") int turId, @RequestBody @Validated RatingDto ratingDto) {
        Tur tur = verifyTur(turId);
        turRatingRepository.save(new TurRating( new TurRatingPk(tur, ratingDto.getCustomerId()),
                ratingDto.getScore(), ratingDto.getComment()));
    }



    /**
     * Lookup a the Ratings for a Tur.
     *
     * @param turId
     * @param pageable
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path="")
    public Page<RatingDto> getAllRatingsForTur(@PathVariable(value = "turId") int turId, Pageable pageable) {
        Tur tur = verifyTur(turId);

        Page<TurRating> turRatingPage = turRatingRepository.findByPkTurId(tur.getId(), pageable);
        List<RatingDto> ratingDtoList = turRatingPage.getContent().stream().map(turRating -> toDto(turRating)).collect(Collectors.toList());

        return new PageImpl<RatingDto>(ratingDtoList, pageable, turRatingPage.getTotalPages());
    }



    /**
     * Calculate the average Score of a Tur.
     *
     * @param turId
     * @return Tuple of "average" and the average value.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/average")
    public AbstractMap.SimpleEntry<String, Double> getAverage(@PathVariable(value = "turId") int turId) {
        Tur Tur = verifyTur(turId);

        List<TurRating> ratings = turRatingRepository.findByPkTurId(turId);
        OptionalDouble average = ratings.stream().mapToInt(TurRating::getScore).average();
        double result = average.isPresent() ? average.getAsDouble():null;

        return new AbstractMap.SimpleEntry<String, Double>("average", result);
    }




    /**
     * Update score and comment of a Tur Rating
     *
     * @param turId
     * @param ratingDto
     * @return The modified Rating DTO.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public RatingDto updateWithPut(@PathVariable(value = "turId") int turId, @RequestBody @Validated RatingDto ratingDto) {
        TurRating rating = verifyTurRating(turId, ratingDto.getCustomerId());

        rating.setScore(ratingDto.getScore());
        rating.setComment(ratingDto.getComment());

        return toDto(turRatingRepository.save(rating));
    }



    /**
     * Update score or comment of a Tur Rating
     *
     * @param turId
     * @param ratingDto
     * @return The modified Rating DTO.
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public RatingDto updateWithPatch(@PathVariable(value = "turId") int turId, @RequestBody @Validated RatingDto ratingDto) {
        TurRating rating = verifyTurRating(turId, ratingDto.getCustomerId());

        if (ratingDto.getScore() != null) {
            rating.setScore(ratingDto.getScore());
        }
        if (ratingDto.getComment() != null) {
            rating.setComment(ratingDto.getComment());
        }

        return toDto(turRatingRepository.save(rating));
    }




    /**
     * Delete a Rating of a Tur made by a customer
     *
     * @param turId
     * @param customerId
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{customerId}")
    public void delete(@PathVariable(value = "turId") int turId, @PathVariable(value = "customerId") int customerId) {
        TurRating rating = verifyTurRating(turId, customerId);
        turRatingRepository.delete(rating);
    }




    /**
     * Convert the TurRating entity to a RatingDto
     *
     * @param turRating
     * @return RatingDto
     */
    private RatingDto toDto(TurRating turRating) {
        return new RatingDto(turRating.getScore(), turRating.getComment(), turRating.getPk().getCustomerId());
    }







    /**
     * Verify and return the TurRating for a particular TurId and Customer
     * @param turId
     * @param customerId
     * @return the found TurRating
     * @throws NoSuchElementException if no TurRating found
     */
    private TurRating verifyTurRating(int turId, int customerId) throws NoSuchElementException {
        TurRating rating = turRatingRepository.findByPkTurIdAndPkCustomerId(turId, customerId);
        if (rating == null) {
            throw new NoSuchElementException("Tur-Rating pair for request("
                    + turId + " for customer" + customerId);
        }
        return rating;
    }




    /**
     * Verify and return the Tur given a TurId.
     *
     * @param turId
     * @return the found Tur
     * @throws NoSuchElementException if no Tur found.
     */
    private Tur verifyTur(int turId) throws NoSuchElementException {
        Optional<Tur> tur = turRepo.findById(turId);
        if (!tur.isPresent()) {
            throw new NoSuchElementException("Tur does not exist " + turId);
        }
        return tur.get();
    }




    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();

    }

}
