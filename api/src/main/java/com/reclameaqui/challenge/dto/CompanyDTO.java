package com.reclameaqui.challenge.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    
    private String id;
    @NotBlank(message = "name not be empty")
    private String name;
    @NotBlank(message = "cnpj not be empty")
    private String cnpj;
    @Valid
    private LocaleDTO locale;
}
