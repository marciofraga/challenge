package com.reclameaqui.challenge.service;

import java.util.List;

import com.reclameaqui.challenge.dto.CompanyDTO;
import com.reclameaqui.challenge.model.Company;

public interface CompanyService {
    
    List<Company> findAll();
    Company findById(String cnpj);
    Company create(CompanyDTO complain);
    Company update(CompanyDTO complain, String id);
    void remove(String id);
}
