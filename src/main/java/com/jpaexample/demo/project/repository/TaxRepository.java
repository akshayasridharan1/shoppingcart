package com.jpaexample.demo.project.repository;

import com.jpaexample.demo.project.model.Tax;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends CrudRepository<Tax,Long> {

    Tax findByTaxType(String taxType);
}
