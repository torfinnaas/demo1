package com.torfinn.demo1.web;


import com.torfinn.demo1.domain.Tur;
import com.torfinn.demo1.domain.TurRating;
import com.torfinn.demo1.repo.TurRatingRepository;
import com.torfinn.demo1.repo.TurRepo;
import com.torfinn.demo1.service.TurRatingService;
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
    TurRatingService turRatingService;

    @Autowired
    public TurRatingController(TurRatingService turRatingService) {
        this.turRatingService = turRatingService;
    }

    protected TurRatingController() {

    }




    /**
     * Create a Tur Rating.
     *
     * @param turId
     * @param ratingDto
     */
    @PostMapping(path="")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTurRating(@PathVariable(value = "turId") int turId, @RequestBody @Validated RatingDto ratingDto) {
        turRatingService.createNew(turId, ratingDto.getCustomerId(), ratingDto.getScore(), ratingDto.getComment());
    }

    /**
     * Create Several Tour Ratings for one tour, score and several customers.
     *
     * @param turId
     * @param score
     * @param customers
     */
    @PostMapping("/{score}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createManyTourRatings(@PathVariable(value = "turId") int turId,
                                      @PathVariable(value = "score") int score,
                                      @RequestParam("customers") Integer customers[]) {
        turRatingService.rateMany(turId, score, customers);
    }


    /**
     * Lookup a the Ratings for a Tur.
     *
     * @param turId
     * @param pageable
     * @return
     */
    @GetMapping(path="")
    public Page<RatingDto> getAllRatingsForTur(@PathVariable(value = "turId") int turId, Pageable pageable) {
        Page<TurRating> turRatingPage = turRatingService.lookupRatings(turId, pageable);
        List<RatingDto> ratingDtoList = turRatingPage.getContent().stream().map(turRating -> toDto(turRating)).collect(Collectors.toList());

        return new PageImpl<RatingDto>(ratingDtoList, pageable, turRatingPage.getTotalPages());
    }



    /**
     * Calculate the average Score of a Tur.
     *
     * @param turId
     * @return Tuple of "average" and the average value.
     */
    @GetMapping(path = "/average")
    public AbstractMap.SimpleEntry<String, Double> getAverage(@PathVariable(value = "turId") int turId) {
        return new AbstractMap.SimpleEntry<String, Double>("average", turRatingService.getAverageScore(turId));
    }




    /**
     * Update score and comment of a Tur Rating
     *
     * @param turId
     * @param ratingDto
     * @return The modified Rating DTO.
     */
    @PutMapping()
    public RatingDto updateWithPut(@PathVariable(value = "turId") int turId, @RequestBody @Validated RatingDto ratingDto) {
        return toDto(turRatingService.update(turId, ratingDto.getCustomerId(), ratingDto.getScore(),
                ratingDto.getComment()));
    }



    /**
     * Update score or comment of a Tur Rating
     *
     * @param turId
     * @param ratingDto
     * @return The modified Rating DTO.
     */
    @PatchMapping()
    public RatingDto updateWithPatch(@PathVariable(value = "turId") int turId, @RequestBody @Validated RatingDto ratingDto) {
        return toDto(turRatingService.updateSome(turId, ratingDto.getCustomerId(), ratingDto.getScore(),
                ratingDto.getComment()));
    }




    /**
     * Delete a Rating of a Tur made by a customer
     *
     * @param turId
     * @param customerId
     */
    @DeleteMapping(path = "/{customerId}")
    public void delete(@PathVariable(value = "turId") int turId, @PathVariable(value = "customerId") int customerId) {

        turRatingService.delete(turId, customerId);
    }




    /**
     * Convert the TurRating entity to a RatingDto
     *
     * @param turRating
     * @return RatingDto
     */
    private RatingDto toDto(TurRating turRating) {
        return new RatingDto(turRating.getScore(), turRating.getComment(), turRating.getCustomerId());
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
