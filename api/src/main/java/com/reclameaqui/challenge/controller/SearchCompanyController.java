package com.reclameaqui.challenge.controller;

import java.util.List;

import com.reclameaqui.challenge.model.Complaint;
import com.reclameaqui.challenge.service.ComplaintServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private ComplaintServiceImpl complaintService;

    @ApiOperation(value = "return all complaints from a specific company")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return all complaints from a specific company"),
        @ApiResponse(code = 404, message = "no complaints found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @GetMapping("/{cnpjCompany}")
    @ResponseBody
    public ResponseEntity<List<Complaint>> findAllByCompany(@PathVariable("cnpjCompany") String cnpjCompany) {
        List<Complaint> complaints = this.complaintService.findAllByCnpjCompany(cnpjCompany);
        return ResponseEntity.ok().body(complaints);
    }

    @ApiOperation(value = "return all complaints from a specific company has in specific locale")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return all complaints from a specific company has in specific locale"),
        @ApiResponse(code = 404, message = "no complaints found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @GetMapping("/{cnpjCompany}/locale/{state}")
    @ResponseBody
    public ResponseEntity<List<Complaint>> findAllByCompanyLocaleState(
            @PathVariable("cnpjCompany") String cnpjCompany, 
            @PathVariable("state") String state) {
        
        List<Complaint> complaints = this.complaintService.findAllByCnpjCompanyState(cnpjCompany, state);
        return ResponseEntity.ok().body(complaints);
    }

    @GetMapping("/{cnpjCompany}/locale/{state}/{city}")
    @ResponseBody
    public ResponseEntity<List<Complaint>> findAllByCompanyLocaleStateCity(
            @PathVariable("cnpjCompany") String cnpjCompany, 
            @PathVariable("state") String state,
            @PathVariable("city") String city) {
        
        List<Complaint> complains= this.complaintService.findAllByCnpjCompanyStateCity(cnpjCompany, state, city);
        return ResponseEntity.ok().body(complains);
    }
}
