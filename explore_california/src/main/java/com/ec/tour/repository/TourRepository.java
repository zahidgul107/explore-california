package com.ec.tour.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.ec.tour.domain.Tour;

public interface TourRepository extends PagingAndSortingRepository<Tour, String>{
	
	Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable pageable);
	
	@Query(value = "{'tourPackageCode' : ?0}", fields = "{'id':1, 'title':1, 'tourPackageCode':1, 'tourPackageName':1}")
	Page<Tour> findSummaryByTourPackageCode(@Param("code") String code, Pageable pageable);
	
	@Override
    @RestResource(exported = false)
    <S extends Tour> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends Tour> List<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(String string);

    @Override
    @RestResource(exported = false)
    void delete(Tour tour);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Tour> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}
