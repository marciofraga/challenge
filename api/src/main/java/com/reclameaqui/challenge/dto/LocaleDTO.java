package com.reclameaqui.challenge.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** class dto that represent locale fields that user view  */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocaleDTO {
    
    /** locale state */
    @NotBlank(message = "state no be empty")
    private String state;

    /** locale city */
    @NotBlank(message = "city no be empty")
    private String city;
}