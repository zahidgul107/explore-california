package com.ec.tour.domain;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TourRating {
    
	@Id
    private String id;
	
	private String tourId;
     
    @NotNull
    private Integer customerId;
    
    @Min(0)
    @Max(5)
    private Integer score;
    
    @Size(max = 255)
    private String comment;

    

	public TourRating(String tourId, @NotNull Integer customerId, @Min(0) @Max(5) Integer score,
			@Size(max = 255) String comment) {
		super();
		this.tourId = tourId;
		this.customerId = customerId;
		this.score = score;
		this.comment = comment;
	}

	protected TourRating() {
        super();
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTourId() {
		return tourId;
	}

	public void setTourId(String tourId) {
		this.tourId = tourId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

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
}
