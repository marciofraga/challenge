package com.reclameaqui.challenge.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocaleDTO {
    
    @NotBlank(message = "state no be empty")
    private String state;
    @NotBlank(message = "city no be empty")
    private String city;
}
