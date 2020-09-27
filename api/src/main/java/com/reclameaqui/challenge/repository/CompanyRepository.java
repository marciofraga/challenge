package com.reclameaqui.challenge.repository;

import java.util.List;
import java.util.Optional;

import com.reclameaqui.challenge.model.Company;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
    
    List<Company> findByCnpj(String cnpj);
    Optional<Company> findById(String id);
}
