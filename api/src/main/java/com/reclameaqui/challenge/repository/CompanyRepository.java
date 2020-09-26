package com.reclameaqui.challenge.repository;

import com.reclameaqui.challenge.model.Company;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Company findByCnpj(String cnpj);
}
