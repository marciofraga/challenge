package com.reclameaqui.challenge.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Company {
    
    @Id
    private String id;
    private String name;
    private String cnpj;
    @DBRef(lazy = true)
    @JsonIgnoreProperties(value = { "target" })
    private Locale locale;
    @DBRef(lazy = true)
    @JsonBackReference
    private List<Complaint> complaints;
}
