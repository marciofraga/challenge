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

/** Controller responsible for searching complaints by companies, state and city */
@RestController
@RequestMapping("/public/companies")
public class SearchCompanyController {
    
    @Autowired
    private ComplaintServiceImpl complaintService;

    /**
     * method to search all complaints that a company has.
     * 
     * @param cnpjCompany - cnpj field to indentify a specific company
     * @return ResponseEntity<List<Complaint>> - In case success, return 200 HTTP response with all complaints founded.
     * In case there is no found complaints to a company, return 404 HTTP response with message 'no complaints found'.
     */
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

    /**
     * method to search all complaints that a company has in a specific state
     * 
     * @param cnpjCompany - cnpj field to identify a specific company.
     * @param state - state that user trying to search
     * @return ResponseEntity<List<Complaint>> - In case success, return 200 HTTP response with all complaints founded.
     * In case there is no found complaints to a company, return 404 HTTP response with message 'no complaints found'.
     */
    @ApiOperation(value = "return all complaints from a specific company has in specific state")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return all complaints from a specific company has in specific state"),
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

    /**
     * method to search all complaints that a company has in a specific state and city
     * 
     * @param cnpjCompany - cnpj field to identify a specific company.
     * @param state - state that user trying to search
     * @param city - city that user trying to search
     * @return ResponseEntity<List<Complaint>> - In case success, return 200 HTTP response with all complaints founded.
     * In case there is no found complaints to a company, return 404 HTTP response with message 'no complaints found'
     */
    @ApiOperation(value = "return all complaints from a specific company has in specific state and city")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return all complaints from a specific company has in specific state and city"),
        @ApiResponse(code = 404, message = "no complaints found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
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
