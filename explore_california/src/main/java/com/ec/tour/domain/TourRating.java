package com.ec.tour.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class TourRating {
    
    @EmbeddedId
    private TourRatingPk pk;
    
    @Column(nullable = false)
    private Integer score;
    
    @Column
    private String comment;

    public TourRating(TourRatingPk pk, Integer score, String comment) {
		this.pk = pk;
		this.score = score;
		this.comment = comment;
	}

	protected TourRating() {
        super();
    }

    public TourRatingPk getPk() {
        return pk;
    }

    public void setPk(TourRatingPk pk) {
        this.pk = pk;
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
