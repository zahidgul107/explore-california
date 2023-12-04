package com.ec.tour.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.ec.tour.domain.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer>{
	
	Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable pageable);
	
	@Override
    @RestResource(exported = false)
    <S extends Tour> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends Tour> List<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

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
