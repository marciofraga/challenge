package com.reclameaqui.challenge.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.reclameaqui.challenge.dto.ComplaintDTO;
import com.reclameaqui.challenge.model.Complaint;
import com.reclameaqui.challenge.service.ComplaintServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/** Controller responsible for managing complaints resources */
@RestController
@RequestMapping("/private/complaints")
public class ComplaintController {
    
    @Autowired
    private ComplaintServiceImpl complainService;

     /**
     * method to retrieve all information about complaints
     * 
     * @return ResponseEntity<List<Complaint>> - In case success, return an 200 HTTP response with a list of stored complaints.
     * In case there is no found complaints, return 404 HTTP response with message 'no complaints found'.
     * In case there is no authorization, return 401 HTTP response with message 'unauthorized'.
     */
    @ApiOperation(value = "return a list of complaints",
        authorizations = { @Authorization(value = "oauth2") })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return a list of complaints"),
        @ApiResponse(code = 401, message = "unauthorized"),
        @ApiResponse(code = 404, message = "no complaints found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Complaint>> findAll() {
        List<Complaint> complains = this.complainService.findAll();
        return ResponseEntity.ok().body(complains);
    }

    /**
     * method to retrieve information about a specific complaint
     * 
     * @param id - the indetifier for resource that user trying retrieve
     * @return ResponseEntity<Complaint> - In case success, return an 200 HTTP response with a complaint.
     * In case there is no found complaint, return 404 HTTP response with message 'no complaint found'.
     * In case there is no authorization, return 401 HTTP response with message 'unauthorized'.
     */
    @ApiOperation(value = "return one specific complaint",
        authorizations = { @Authorization(value = "oauth2") })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return one specific complaint"),
        @ApiResponse(code = 401, message = "unauthorized"),
        @ApiResponse(code = 404, message = "no complaints found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Complaint> findById(@PathVariable("id") String id) {
        Complaint complain = this.complainService.findById(id);
        return ResponseEntity.ok().body(complain);
    }

    /**
     * method to save a new complaint in database 
     * 
     * @param complaintDto - object with informations about complaint like title, description and company
     * @return Responseentity<Complaint> - In case success, return an 200 HTTP response with a new complaint saved.
     * If one of the fields is missing (title, description and company), return 422 HTTP response with message 
     * 'validation error' and especifying which field is missing.
     * In case there is no authorization, return 401 HTTP response with message 'unauthorized'.
     */
    @ApiOperation(value = "save one complaint",
        authorizations = { @Authorization(value = "oauth2") })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "return a saved complaint"),
        @ApiResponse(code = 401, message = "unauthorized"),
        @ApiResponse(code = 422, message = "validation error"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @PostMapping
    @ResponseBody
    public ResponseEntity<Complaint> create(@Valid @RequestBody ComplaintDTO complain) {
        Complaint obj = this.complainService.create(complain);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    /**
     * method to update a specific complaint with new informations passed in parameter
     * 
     * @param id - the indetifier for resource that user trying updated
     * @param complaintDto - object with informations about complaint like title, description and company.
     * @return ResponseEntity<ComplaintDTO> - In case success, return 200 HTTP response with a complaint updated.
     * If one of the fields is missing (title, description and company), return 422 HTTP response with message 'validation error' 
     * and especifying which field is missing.
     * In case there is no authorization, return 401 HTTP response with message 'unauthorized'.
     */
    @ApiOperation(value = "update one specific complaint",
        authorizations = { @Authorization(value = "oauth2") })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return a updated complaint"),
        @ApiResponse(code = 401, message = "unauthorized"),
        @ApiResponse(code = 422, message = "validation error"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Complaint> update(
            @Valid @RequestBody ComplaintDTO complain, 
            @PathVariable("id") String id) {
        Complaint obj = this.complainService.update(complain, id);
        return ResponseEntity.ok().body(obj);
    }

    /**
     * method to remove a specific complaint
     * 
     * @param id - the indetifier for resource that user trying remove
     * @return ResponseEntity<Void> - In case success, return 204 Http response with no content.
     * In case there is no found complaint, return 404 HTTP response with message 'no complaint found'.
     * In case there is no authorization, return 401 HTTP response with message 'unauthorized'.
     */
    @ApiOperation(value = "remove one specific complaint",
        authorizations = { @Authorization(value = "oauth2") })
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "complaint succesfully removed"),
        @ApiResponse(code = 401, message = "unauthorized"),
        @ApiResponse(code = 404, message = "no complaint found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> remove(@PathVariable("id") String id) {
        this.complainService.remove(id);
        return ResponseEntity.noContent().build();
    }
}