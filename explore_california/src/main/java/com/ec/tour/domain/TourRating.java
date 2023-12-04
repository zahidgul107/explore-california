package com.ec.tour.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class TourRating {
	
	@EmbeddedId
	private TourRatingPk pk;
	

}
