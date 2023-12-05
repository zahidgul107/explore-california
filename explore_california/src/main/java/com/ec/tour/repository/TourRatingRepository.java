package com.ec.tour.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ec.tour.domain.TourRating;
import com.ec.tour.domain.TourRatingPk;

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends JpaRepository<TourRating, String> {
	
	List<TourRating> findByTourId(String tourId);
	
	Optional<TourRating> findByTourIdAndCustomerId(String tourId, Integer customerId);
	
	Page<TourRating> findByTourId(String tourId, Pageable pageable);

}
