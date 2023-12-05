package com.ec.tour.web;

import com.ec.tour.domain.TourRating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RatingDto {
	
	@Min(0)
	@Max(5)
	private Integer score;
	
	@Size(max = 255)
	private String comment;
	
	@NotNull
	private Integer customerId;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	

	public RatingDto(@Min(0) @Max(5) Integer score, @Size(max = 255) String comment, @NotNull Integer customerId) {
		this.score = score;
		this.comment = comment;
		this.customerId = customerId;
	}

	public RatingDto(TourRating rating) {
		this.score = rating.getScore();
		this.comment = rating.getComment();
		this.customerId = rating.getPk().getCustomerId();
	}

	

	
	
	

}
