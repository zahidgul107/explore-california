package com.ec.tour.domain;

import java.util.Map;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Tour contains all attributes of an Explore California Tour.
 *
 * Created by Mary Ellen Bowman
 */
@Document
public class Tour {
	
	@Id
    private String id;

    private String title;

    @Indexed
    private String tourPackageCode;
    
    private String tourPackageName;

    private Map<String, String> details;

    protected Tour() {
    }

    

    public Tour(String title, TourPackage tourPackage, Map<String, String> details) {
		this.title = title;
		this.tourPackageCode = tourPackage.getCode();
		this.tourPackageName = tourPackage.getName();
		this.details = details;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTourPackageCode() {
		return tourPackageCode;
	}



	public void setTourPackageCode(String tourPackageCode) {
		this.tourPackageCode = tourPackageCode;
	}



	public String getTourPackageName() {
		return tourPackageName;
	}



	public void setTourPackageName(String tourPackageName) {
		this.tourPackageName = tourPackageName;
	}



	public Map<String, String> getDetails() {
		return details;
	}



	public void setDetails(Map<String, String> details) {
		this.details = details;
	}



	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    

    

    @Override
	public String toString() {
		return "Tour [id=" + id + ", title=" + title + ", tourPackageCode=" + tourPackageCode + ", tourPackageName="
				+ tourPackageName + ", details=" + details + "]";
	}



	

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tour other = (Tour) obj;
		return Objects.equals(details, other.details) && Objects.equals(id, other.id)
				&& Objects.equals(title, other.title) && Objects.equals(tourPackageCode, other.tourPackageCode)
				&& Objects.equals(tourPackageName, other.tourPackageName);
	}



	@Override
	public int hashCode() {
		return Objects.hash(details, id, title, tourPackageCode, tourPackageName);
	}

}
