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
@RequestMapping("/public/locale")
public class SearchLocaleController {
    
    @ApiOperation(value = "return all complains from a specific locale")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "return all complains from a specific locale"),
        @ApiResponse(code = 404, message = "no complains found"),
        @ApiResponse(code = 500, message = "an exception happened")
    })
    @GetMapping("/locale/{locale}")
    @ResponseBody
    public ResponseEntity<List<Complaint>> findAllByLocale(@PathVariable("locale") String locale) {
        //List<Complaint> complains;// = this.complainService.findAllByLocale(locale);
        return ResponseEntity.ok().body(null);
    }
}
