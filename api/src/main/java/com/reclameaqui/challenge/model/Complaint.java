package com.reclameaqui.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Complaint {
    
    @Id
    private String id;

    private String title;
    private String description;
    @DBRef(lazy = true)
    @JsonIgnoreProperties(value = { "target" })
    private Company company;
}
