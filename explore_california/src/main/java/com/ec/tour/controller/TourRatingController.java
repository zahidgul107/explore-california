package com.ec.tour.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ec.tour.domain.Tour;
import com.ec.tour.domain.TourRating;
import com.ec.tour.domain.TourRatingPk;
import com.ec.tour.repository.TourRatingRepository;
import com.ec.tour.repository.TourRepository;
import com.ec.tour.web.RatingDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {

	TourRatingRepository tourRatingRepo;
	TourRepository tourRepo;
	
	@Autowired
	public TourRatingController(TourRatingRepository tourRatingRepo, TourRepository tourRepo) {
		this.tourRatingRepo = tourRatingRepo;
		this.tourRepo = tourRepo;
	}
	
	protected TourRatingController() {
	}
	
	/**
	 * @param tourId
	 * @param ratingDto
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTourRating(@PathVariable int tourId,@Valid @RequestBody RatingDto ratingDto) {
		Tour tour = verifyTour(tourId);
		tourRatingRepo.save(new TourRating(new TourRatingPk(tour, ratingDto.getCustomerId()), ratingDto.getScore(),
				ratingDto.getComment()));
	}
	
	
	@GetMapping
	public Page<RatingDto> getAllRatingForTour(@PathVariable int tourId, Pageable pageable) {
		verifyTour(tourId);
		Page<TourRating> ratings = tourRatingRepo.findByPkTourId(tourId, pageable);
		return new PageImpl<>(ratings.get().map(RatingDto::new).collect(Collectors.toList()), pageable, ratings.getTotalElements()
				);
	}
	
	/**
	 * @param tourId
	 * @return
	 */
/*	@GetMapping
	public List<RatingDto> getAllRatingsForTour(@PathVariable int tourId) {
	    verifyTour(tourId);

	    return tourRatingRepo.findByPkTourId(tourId)
	            .stream()
	            .map(tourRating -> new RatingDto(
	                    tourRating.getScore(), 
	                    tourRating.getComment(),
	                    tourRating.getPk().getCustomerId()
	            ))
	            .collect(Collectors.toList());
	}
	*/
	
	/**
	 * @param tourId
	 * @return
	 */
	@GetMapping(path = "/average")
	public Map<String, Double> getAverage(@PathVariable int tourId) {
		verifyTour(tourId);
		return Map.of("average", tourRatingRepo.findByPkTourId(tourId).stream()
				.mapToInt(TourRating::getScore).average()
				.orElseThrow(() ->
						new NoSuchElementException("Tour has no rating")));
	}
	
	@PutMapping
	public RatingDto updateRatingPut(@PathVariable int tourId,@RequestBody @Validated RatingDto ratingDto) {
		TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
		rating.setScore(ratingDto.getScore());
		rating.setComment(ratingDto.getComment());
		tourRatingRepo.save(rating);
		RatingDto responseRatingDto = new RatingDto(rating);
		return responseRatingDto; 
	}
	
	@PatchMapping
	public RatingDto updateRatingWithPatch(@PathVariable int tourId, @RequestBody @Validated RatingDto ratingDto) {
		TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
		if (ratingDto.getScore() != null) {
			rating.setScore(ratingDto.getScore());
		}
		if (ratingDto.getComment() != null) {
			rating.setComment(ratingDto.getComment());
		}
		tourRatingRepo.save(rating);
		RatingDto responseRatingDto = new RatingDto(rating);
		return responseRatingDto;
	} 
	
	@DeleteMapping(path = "/{customerId}")
	public void delete(@PathVariable int tourId, @PathVariable int customerId) {
		TourRating rating = verifyTourRating(tourId, customerId);
		tourRatingRepo.delete(rating);
	}
	
	private TourRating verifyTourRating(int tourId, int customerId) throws NoSuchElementException {
		return tourRatingRepo.findByPkTourIdAndPkCustomerId(tourId, customerId).orElseThrow(() ->
			new NoSuchElementException("Tour-Rating pair for request(" + tourId + " for customer " + customerId)
		);
	}
	
	
	/**
	 * @param tourId
	 * @return
	 * @throws NoSuchElementException
	 */
	private Tour verifyTour(int tourId) throws NoSuchElementException {
		return tourRepo.findById(tourId).orElseThrow(() ->
			new NoSuchElementException("Tour does not exist " + tourId)
		);
	}
	
	/**
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public String return400(NoSuchElementException ex) {
		return ex.getMessage();
	}

	
	
	
	

}
