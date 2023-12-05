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
public interface TourRatingRepository extends JpaRepository<TourRating, TourRatingPk> {
	
	List<TourRating> findByPkTourId(Integer tourId);
	
	Optional<TourRating> findByPkTourIdAndPkCustomerId(Integer tourId, Integer customerId);
	
	Page<TourRating> findByPkTourId(Integer tourId, Pageable pageable);

}
