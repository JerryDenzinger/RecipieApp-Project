package com.jerry.www.RecipeApp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jerry.www.RecipeApp.model.UnitOfMesure;

public interface UnitOfMesureRepository extends CrudRepository<UnitOfMesure, Long> {

	Optional<UnitOfMesure> findByDescription(String description);

}
