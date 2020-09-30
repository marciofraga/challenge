package com.reclameaqui.challenge.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** class dto that represent company fields that user view  */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    
    /** primary key of this company */
    @ApiModelProperty(hidden = true)
    private String id;
    
    /**company name */
    @ApiModelProperty(value = "company name")
    @NotBlank(message = "name not be empty")
    private String name;
    
    /** company cnpj*/
    @ApiModelProperty(value = "company cnpj")
    @NotBlank(message = "cnpj not be empty")
    private String cnpj;
    
    /** company location */
    @ApiModelProperty(value = "company location")
    @Valid
    private LocaleDTO locale;
}
