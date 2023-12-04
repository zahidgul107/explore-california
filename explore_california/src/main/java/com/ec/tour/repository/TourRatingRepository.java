package com.ec.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ec.tour.domain.TourRating;
import com.ec.tour.domain.TourRatingPk;

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends JpaRepository<TourRating, TourRatingPk> {
	
	

}
