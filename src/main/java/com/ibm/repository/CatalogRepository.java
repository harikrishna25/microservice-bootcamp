package com.ibm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.model.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {

	Optional<Catalog> findByItem(String item);

}
