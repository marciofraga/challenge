package com.reclameaqui.challenge.repository;

import java.util.List;

import com.reclameaqui.challenge.model.Complaint;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ComplaintRepository extends MongoRepository<Complaint, String>{
    
    @Query("{ 'locale': {'$regex': ?0, '$options': 'i'} }")
    List<Complaint> findByLocale(String locale);
    @Query("{ 'company': {'$regex': ?0, '$options': 'i'} }")
    List<Complaint> findAllByCompany(String company);

    @Query("{ $and: [ {'company': {'$regex': ?0, '$options': 'i'} }, {'locale': {'$regex': ?1, '$options': 'i'} } ] }")
	List<Complaint> findByCompanyLocale(String company, String locale);
}
