package com.reclameaqui.challenge.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ComplaintDTO {
    
    private String id;
    @ApiModelProperty(value = "complain title")
    @NotBlank(message = "title not be empty")
    @NonNull
    private String title;
    
    @ApiModelProperty(value = "complain description")
    @NotBlank(message = "description not be empty")
    @NonNull
    private String description;

    @ApiModelProperty(value = "id company where the complaint happened")
    @NotBlank(message = "id not be empty")
    @NonNull    
    private String idCompany;
}
