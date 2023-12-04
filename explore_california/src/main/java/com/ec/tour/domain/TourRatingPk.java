package com.ec.tour.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class TourRatingPk implements Serializable {
	
	@ManyToOne
	private Tour tour;
	
	@Column(insertable = false, updatable = false, nullable = false)
	private Integer customerId;

	public TourRatingPk() {
	}
	
	

}
