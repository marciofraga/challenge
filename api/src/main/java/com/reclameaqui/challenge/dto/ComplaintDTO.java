package com.reclameaqui.challenge.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** class dto that represent complaint fields that user view  */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ComplaintDTO {
    
    /**primary key of this complaint */
    @ApiModelProperty(hidden = true)
    private String id;

    /**complaint title */
    @ApiModelProperty(value = "complaint title")
    @NotBlank(message = "title not be empty")
    @NonNull
    private String title;
    
    /**complaint description */
    @ApiModelProperty(value = "complaint description")
    @NotBlank(message = "description not be empty")
    @NonNull
    private String description;

    /** primary key company */
    @ApiModelProperty(value = "id company where the complaint happened")
    @NotBlank(message = "id not be empty")
    @NonNull    
    private String idCompany;
}
