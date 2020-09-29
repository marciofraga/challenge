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

/** represent the interface CompanyService implementation with all service used to controllers */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private LocaleRepository localeRepository;

    /**
     * method to retrieve all companies stored
     */
    @Override
    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    /**
     * Method to retrive a specific company. In case there is no company found, 
     * an exception is thrown with message 'company doesnt exist'
     * 
     * @param id - represent the primary key
     */
    @Override
    public Company findById(String id) {
        return this.companyRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("company doesnt exist"));
    }

    /**
     * Method to save a new company in database.
     * @param companyDto - represent a fields that user want saved
     */
    @Override
    public Company create(CompanyDTO companyDto) {
        //searching if already has a locale with state and city
        Locale locale = this.localeRepository.findByStateCity(
                        companyDto.getLocale().getState(), 
                        companyDto.getLocale().getCity());
        //if no found locale, used a state and city passed by user
        if(locale == null) {
            locale = new Locale(companyDto.getLocale().getState(), 
                        companyDto.getLocale().getCity());
            locale = this.localeRepository.save(locale);
        }
        //convert companyDto to company object
        Company company = fromDTO(companyDto);
        // set locale company
        company.setLocale(locale);
        //save a new company
        return this.companyRepository.save(company);
    }

    /**
     * method to update a specific company with new values passed by user. In case there is no found a company,
     * an exception is thrown with message 'company doesnt exist'
     * 
     * @param companyDto - represent a fields with new values
     * @param id - represent primary key company
     */
    @Override
    public Company update(CompanyDTO companyDto, String id) {
        //searching if a company exist. If there is no found, an excpetion is thrown.
        Company companyExist = this.companyRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("company doesnt exist"));
        
        //code block that set primary key, state and city locale and convert companyDto to company object
        companyDto.setId(id);
        Company company = fromDTO(companyDto);
        company.setLocale(companyExist.getLocale());
        company.getLocale().setCity(companyDto.getLocale().getCity());
        company.getLocale().setState(companyDto.getLocale().getState());
        //update company
        return this.companyRepository.save(company);
    }

    /**
     * method to remove a specific company by primary key passed for user. If there is no found a company, 
     * an exception is throw
     * 
     * @param id - represent a primary key
     */
    @Override
    public void remove(String id) {
        //searching if a company exist. If there is no found, an exception is throw
        this.companyRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("company doesnt exist"));
        //remove a company
        this.companyRepository.deleteById(id);
    }
    
    /**
     * private method to convert a CompanyDTO object to a Company object
     * 
     * @param companyDto - represent a companydto object
     */
    private Company fromDTO(CompanyDTO companyDto) {
        return new Company(companyDto.getId(), 
                companyDto.getName(), 
                companyDto.getCnpj(), 
                null);
    }
}
