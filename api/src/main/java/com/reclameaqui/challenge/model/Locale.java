package com.reclameaqui.challenge.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** represent a locale that company is */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Document
public class Locale {
    
    /** primary key */
    @Id
    private String id;
    /** state locale */
    @NonNull
    private String state;
    /** city locale */
    @NonNull
    private String city;
}
