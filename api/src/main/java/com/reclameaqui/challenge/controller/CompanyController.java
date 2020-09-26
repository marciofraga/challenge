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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/companies")
public class CompanyController {
 
    @Autowired
    private CompanyServiceImpl companyService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Company>> findAll() {
        List<Company> companies = this.companyService.findAll();
        return ResponseEntity.ok().body(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") String id) {
        Company company = this.companyService.findById(id);
        return ResponseEntity.ok().body(company);
    }

    @PostMapping
    public ResponseEntity<Company> create(@Valid @RequestBody CompanyDTO companyDto) {
        Company obj = this.companyService.create(companyDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> update(@PathVariable("id") String id, @Valid @RequestBody CompanyDTO companyDto) {
        Company obj = this.companyService.update(companyDto, id);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") String id) {
        this.companyService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
