package com.reclameaqui.challenge.repository;

import java.util.List;

import com.reclameaqui.challenge.model.Complaint;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ComplaintRepository extends MongoRepository<Complaint, String>{
    
    @Query("{ 'company.locale.state': {'$regex': ?0, '$options': 'i'} }")
    List<Complaint> findByState(String locale);
    @Query("{ $and: [ " +
        "{'company.locale.state': {'$regex': ?0, '$options': 'i'}}, " +
        "{'company.locale.city': {'$regex': ?1, '$options': 'i'}} ]}")
    List<Complaint> findByStateCity(String state, String city);

    @Query("{ 'company.cnpj': ?0 }")
    List<Complaint> findAllByCnpjCompany(String cnpjCompany);
    @Query("{ $and: [ {'company.cnpj': ?0}, {'company.locale.state': ?1 } ] }")
    List<Complaint> findByCnpjCompanyState(String cnpjCompany, String state);
    @Query("{ $and: [ {'company.cnpj': ?0}, {'company.locale.state': ?1}, {'company.locale.city': ?2 } ] }")
    List<Complaint> findByCnpjCompanyStateCity(String cnpjCompany, String state, String city);
}
