package com.reclameaqui.challenge.repository;

import java.util.List;

import com.reclameaqui.challenge.model.Complaint;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**  Spring Data Mongo CRUD repository for accessing complaints */
public interface ComplaintRepository extends MongoRepository<Complaint, String>{
    
    /** method that find all Complaints by state locale */
    @Query("{ 'company.locale.state': {'$regex': ?0, '$options': 'i'} }")
    List<Complaint> findByState(String locale);

    /** method that find all complaints by state and city locale */
    @Query("{ $and: [ " +
        "{'company.locale.state': {'$regex': ?0, '$options': 'i'}}, " +
        "{'company.locale.city': {'$regex': ?1, '$options': 'i'}} ]}")
    List<Complaint> findByStateCity(String state, String city);

    /** method find all complaints by cnpj company */
    @Query("{ 'company.cnpj': ?0 }")
    List<Complaint> findAllByCnpjCompany(String cnpjCompany);

    /** method find all complaints by cnpj company and state locale */
    @Query("{ $and: [ {'company.cnpj': ?0}, {'company.locale.state': ?1 } ] }")
    List<Complaint> findByCnpjCompanyState(String cnpjCompany, String state);

    /** method find all complaints by cnpj company and state and city locale */
    @Query("{ $and: [ {'company.cnpj': ?0}, {'company.locale.state': ?1}, {'company.locale.city': ?2 } ] }")
    List<Complaint> findByCnpjCompanyStateCity(String cnpjCompany, String state, String city);
}
