package com.reclameaqui.challenge.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Document
public class Locale {
    
    @Id
    private String id;
    @NonNull
    private String state;
    @NonNull
    private String city;
}
