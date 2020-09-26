package com.reclameaqui.challenge.controller;

import java.util.List;

import com.reclameaqui.challenge.model.Complaint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/public/companies")
public class SearchCompanyController {
    
    @ApiOperation(value = "return all complains from a specific company")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return all complains from a specific company"),
        @ApiResponse(code = 404, message = "no complains found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @GetMapping("/company/{company}")
    @ResponseBody
    public ResponseEntity<List<Complaint>> findAllByCompany(@PathVariable("company") String company) {
        //List<Complaint> complains = this.complainService.findAllByCompany(company);
        return null;//ResponseEntity.ok().body(complains);
    }

    @ApiOperation(value = "return all complains from a specific company has in specific locale")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return all complains from a specific company has in specific locale"),
        @ApiResponse(code = 404, message = "no complains found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @GetMapping("/company/{company}/{locale}")
    @ResponseBody
    public ResponseEntity<List<Complaint>> findAllByCompanyLocale(
            @PathVariable("company") String company, 
            @PathVariable("locale") String locale) {
        
        //List<Complaint> complains= this.complainService.findAllByCompanyLocale(company, locale);
        return null; //ResponseEntity.ok().body(complains);
    }
}
