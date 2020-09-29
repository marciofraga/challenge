package com.reclameaqui.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** represent a company that clients might complaints */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Company {
    
    /** primary key */
    @Id
    private String id;
    /**name company */
    private String name;
    /**cnpj company */
    private String cnpj;
    /**locale company */
    @JsonIgnoreProperties(value = { "target" })
    private Locale locale;
}
