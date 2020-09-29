package com.reclameaqui.challenge.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.reclameaqui.challenge.dto.CompanyDTO;
import com.reclameaqui.challenge.model.Company;
import com.reclameaqui.challenge.service.CompanyServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ApiResponse;

/** Controller responsible for managing companies resources */
@RestController
@RequestMapping("/private/companies")
public class CompanyController {
 
    @Autowired
    private CompanyServiceImpl companyService;

    /**
     * method to retrieve all information about companies
     * 
     * @return ResponseEntity<List<Company>> - In case success, return an 200 HTTP response with a list of stored companies.
     * In case there is no found companies, return 404 HTTP response with message 'no companies found'.
     * In case there is no authorization, return 401 HTTP response with message 'unauthorized'.
     */
    @ApiOperation(value = "return a list of companies",
        authorizations = { @Authorization(value = "oauth2") })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return a list of companies"),
        @ApiResponse(code = 404, message = "no companies found"),
        @ApiResponse(code = 401, message = "unauthorized"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        List<Company> companies = this.companyService.findAll();
        return ResponseEntity.ok().body(companies);
    }

    /**
     * method to retrieve information about a specific company
     * @param id - the indetifier for resource that user trying retrieve
     * @return ResponseEntity<Company> - In case success, return an 200 HTTP response with a company.
     * In case there is no found company, return 404 HTTP response with message 'no company found'.
     * In case there is no authorization, return 401 HTTP response with message 'unauthorized'.
     */
    @ApiOperation(value = "return one specific company",
        authorizations = { @Authorization(value = "oauth2") })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return one specific company"),
        @ApiResponse(code = 404, message = "no company found"),
        @ApiResponse(code = 401, message = "unauthorized"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") String id) {
        Company company = this.companyService.findById(id);
        return ResponseEntity.ok().body(company);
    }

    /**
     * method to save a new company in database 
     * 
     * @param companyDto - object with informations about company like name, cnpj and locale
     * @return Responseentity<Company> - In case success, return an 200 HTTP response with a new company saved.
     * If one of the fields is missing (name, cnpj and locale), return 422 HTTP response with message 'validation error' 
     * and especifying which field is missing.
     * In case there is no authorization, return 401 HTTP response with message 'unauthorized'.
     */
    @ApiOperation(value = "save one new company",
        authorizations = { @Authorization(value = "oauth2") })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return saved company"),
        @ApiResponse(code = 422, message = "validation error"),
        @ApiResponse(code = 401, message = "unauthorized"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @PostMapping
    public ResponseEntity<Company> create(@Valid @RequestBody CompanyDTO companyDto) {
        Company obj = this.companyService.create(companyDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    /**
     * method to update a specific company with new informations passed in parameter
     * 
     * @param id - the indetifier for resource that user trying updated
     * @param companyDto - object with informations about company like name, cnpj and locale
     * @return ResponseEntity<Company> - In case success, return 200 HTTP response with a company updated.
     * If one of the fields is missing (name, cnpj and locale), return 422 HTTP response with message 'validation error' 
     * and especifying which field is missing.
     * In case there is no authorization, return 401 HTTP response with message 'unauthorized'.
     */
    @ApiOperation(value = "update a specific company",
        authorizations = { @Authorization(value = "oauth2") })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return a updated companies"),
        @ApiResponse(code = 422, message = "validation error"),
        @ApiResponse(code = 401, message = "unauthorized"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Company> update(@PathVariable("id") String id, @Valid @RequestBody CompanyDTO companyDto) {
        Company obj = this.companyService.update(companyDto, id);
        return ResponseEntity.ok().body(obj);
    }

    /**
     * method to remove a specific company
     * 
     * @param id - the indetifier for resource that user trying remove
     * @return ResponseEntity<Void> - In case success, return 204 Http response with no content.
     * In case there is no found company, return 404 HTTP response with message 'no company found'.
     * In case there is no authorization, return 401 HTTP response with message 'unauthorized'.
     */
    @ApiOperation(value = "remove a specific company",
        authorizations = { @Authorization(value = "oauth2") })
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "company succesfully removed"),
        @ApiResponse(code = 404, message = "no company found"),
        @ApiResponse(code = 401, message = "unauthorized"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") String id) {
        this.companyService.remove(id);
        return ResponseEntity.noContent().build();
    }
}