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
@RequestMapping("/public/locale")
public class SearchLocaleController {
    
    @Autowired
    private ComplaintServiceImpl complaintService;

    @ApiOperation(value = "return all complaints from a specific locale")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return all complaints from a specific locale"),
        @ApiResponse(code = 404, message = "no complaints found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @GetMapping("/{state}")
    @ResponseBody
    public ResponseEntity<List<Complaint>> findAllByLocaleState(@PathVariable("state") String state) {
        List<Complaint> complaints = this.complaintService.findAllByState(state);
        return ResponseEntity.ok().body(complaints);
    }

    @GetMapping("/{state}/{city}")
    @ResponseBody
    public ResponseEntity<List<Complaint>> findAllByLocaleStateCity
            (@PathVariable("state") String state,
            @PathVariable("city") String city) {
        List<Complaint> complaints = this.complaintService.findAllByStatecity(state, city);
        return ResponseEntity.ok().body(complaints);
    }
}
