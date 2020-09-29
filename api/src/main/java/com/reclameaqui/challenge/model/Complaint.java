package com.reclameaqui.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**represent a complaint that user might do with company */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Complaint {
    
    /**primary key */
    @Id
    private String id;
    /** title complaint */
    private String title;
    /** description complaint */
    private String description;
    /** company that user complaint */
    @JsonIgnoreProperties(value = { "target" })
    private Company company;
}