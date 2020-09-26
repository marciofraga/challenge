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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/complaints")
public class ComplainController {
    
    @Autowired
    private ComplaintServiceImpl complainService;

    @ApiOperation(value = "return a list of complaints")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return a list of complaints"),
        @ApiResponse(code = 404, message = "no complaints found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Complaint>> findAll() {
        List<Complaint> complains = this.complainService.findAll();
        return ResponseEntity.ok().body(complains);
    }


    @ApiOperation(value = "return one specific complain")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return one specific complain"),
        @ApiResponse(code = 404, message = "no complains found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Complaint> findById(@PathVariable("id") String id) {
        Complaint complain = this.complainService.findById(id);
        return ResponseEntity.ok().body(complain);
    }


    @ApiOperation(value = "save one complain")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "return a saved complain"),
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


    @ApiOperation(value = "update one specific complain")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return a updated complain"),
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


    @ApiOperation(value = "update one specific complain")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "complain succesfully removed"),
        @ApiResponse(code = 404, message = "no complain found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> remove(@PathVariable("id") String id) {
        this.complainService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
