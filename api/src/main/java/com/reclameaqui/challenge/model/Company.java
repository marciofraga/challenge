package com.reclameaqui.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Id;
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
    @JsonIgnoreProperties(value = { "target" })
    private Locale locale;
}
