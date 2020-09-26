package com.reclameaqui.challenge.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
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
    
    @ApiModelProperty(value = "company name")
    @NotBlank(message = "name not be empty")
    private String name;
    @ApiModelProperty(value = "company cnpj")
    @NotBlank(message = "cnpj not be empty")
    private String cnpj;
    @ApiModelProperty(value = "company location")
    @Valid
    private LocaleDTO locale;
}
