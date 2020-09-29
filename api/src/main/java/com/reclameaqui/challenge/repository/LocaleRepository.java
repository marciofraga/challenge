package com.reclameaqui.challenge.repository;

import java.util.List;

import com.reclameaqui.challenge.model.Locale;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/** Spring Data Mongo CRUD repository for accessing locale */
public interface LocaleRepository extends MongoRepository<Locale, String> {
    
    /** method that find a locale by state and city field */
    @Query("{ $and: [ {'state': {'$regex': /.*?0.*/, '$options': 'i'} }, {'city': {'$regex': /.*?1.*/, '$options': 'i'} } ] }")
    Locale findByStateCity(String state, String city);

    /** method that find all locale by state field */
    @Query(value = "{'state': {'$regex': ?0, '$options': 'i'} }")
    List<Locale> findAllByState(String state);
}
