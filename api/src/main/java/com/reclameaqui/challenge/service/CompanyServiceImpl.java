package com.reclameaqui.challenge.service;

import java.util.List;

import com.reclameaqui.challenge.dto.CompanyDTO;
import com.reclameaqui.challenge.exception.NotFoundException;
import com.reclameaqui.challenge.model.Company;
import com.reclameaqui.challenge.model.Locale;
import com.reclameaqui.challenge.repository.CompanyRepository;
import com.reclameaqui.challenge.repository.LocaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private LocaleRepository localeRepository;

    @Override
    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company findById(String id) {
        return this.companyRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("company doesnt exist"));
    }

    @Override
    public Company create(CompanyDTO companyDto) {
        Locale locale = this.localeRepository.findByStateCity(
                        companyDto.getLocale().getState(), 
                        companyDto.getLocale().getCity());
        if(locale == null) {
            locale = new Locale(companyDto.getLocale().getState(), 
                        companyDto.getLocale().getCity());
            locale = this.localeRepository.save(locale);
        }
        
        Company company = fromDTO(companyDto);
        company.setLocale(locale);
        return this.companyRepository.save(company);
    }

    @Override
    public Company update(CompanyDTO companyDto, String id) {
        Company companyExist = this.companyRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("company doesnt exist"));
        
        companyDto.setId(id);
        Company company = fromDTO(companyDto);
        company.setLocale(companyExist.getLocale());
        company.getLocale().setCity(companyDto.getLocale().getCity());
        company.getLocale().setState(companyDto.getLocale().getState());
        return this.companyRepository.save(company);
    }

    @Override
    public void remove(String id) {
        this.companyRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("company doesnt exist"));
        this.companyRepository.deleteById(id);
    }
    
    private Company fromDTO(CompanyDTO companyDto) {
        return new Company(companyDto.getId(), 
                companyDto.getName(), 
                companyDto.getCnpj(), 
                null);
    }
}
