package com.reclameaqui.challenge.repository;

import java.util.List;

import com.reclameaqui.challenge.model.Locale;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LocaleRepository extends MongoRepository<Locale, String> {
    @Query("{ $and: [ {'state': {'$regex': /.*?0.*/, '$options': 'i'} }, {'city': {'$regex': /.*?1.*/, '$options': 'i'} } ] }")
    Locale findByStateCity(String state, String city);

    @Query(value = "{'state': {'$regex': ?0, '$options': 'i'} }")
    List<Locale> findAllByState(String state);
}
