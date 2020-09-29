package com.reclameaqui.challenge.repository;

import java.util.List;
import java.util.Optional;

import com.reclameaqui.challenge.model.Company;

import org.springframework.data.mongodb.repository.MongoRepository;

/** Spring Data Mongo CRUD repository for accessing company */
public interface CompanyRepository extends MongoRepository<Company, String> {
    /** method that find all company by cnpj */
    List<Company> findByCnpj(String cnpj);
    /** method that find a company by primary key */
    Optional<Company> findById(String id);
}
