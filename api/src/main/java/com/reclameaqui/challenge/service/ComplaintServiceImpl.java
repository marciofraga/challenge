package com.reclameaqui.challenge.service;

import java.util.List;
import java.util.Optional;

import com.reclameaqui.challenge.dto.ComplaintDTO;
import com.reclameaqui.challenge.exception.NotFoundException;
import com.reclameaqui.challenge.model.Company;
import com.reclameaqui.challenge.model.Complaint;
import com.reclameaqui.challenge.repository.CompanyRepository;
import com.reclameaqui.challenge.repository.ComplaintRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** represent the interface ComplaintService implementation with all service used to controllers */
@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private CompanyRepository companyRepository;

    /**
     * method to retrieve all complaints stored
     */
    @Override
    public List<Complaint> findAll() {
        return this.complaintRepository.findAll();
    }

    /**
     * Method to retrive a specific complaint. In case there is no complaint found, 
     * an exception is thrown with message 'complaint not found'
     * @param id - represent a primary key
     */
    @Override
    public Complaint findById(String id) {
        return this.complaintRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("complaint not found"));
    }

    /**
     * method to retrieve all complaints stored for specific company. In case there is no company found,
     * an exceptions is thrown with message 'company not found'. In case there is no compaints, an exception is thrown
     * with message 'no complaints was found for this company'
     */
    @Override
    public List<Complaint> findAllByCnpjCompany(String cnpjCompany) {
        //searching a companies with cnpj passed
        List<Company> company = this.companyRepository.findByCnpj(cnpjCompany);
        //if there is no companies, an exception is thrown
        if(company.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("company not found"));

        //if there is no complaints for this company, an exception is thrown
        List<Complaint> complaints = this.complaintRepository.findAllByCnpjCompany(cnpjCompany);
        if(complaints.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("no complaints was found for this company"));
        //return all complaints found
        return complaints;
    }

    /**
     * method to retrieve all complaints stored for specific company in a specific state.
     * In case there is no company found, an exceptions is thrown with message 'company not found'. 
     * In case there is no compaints, an exception is thrown
     * with message 'no complaints was found for this company'
     * 
     * @param cnpjCompany - represent a cnpj company
     * @param state - represent state locale
     */
    @Override
    public List<Complaint> findAllByCnpjCompanyState(String cnpjCompany, String state) {
        //searching a companies with cnpj passed
        List<Company> company = this.companyRepository.findByCnpj(cnpjCompany);
        //if there is no companies, an exception is thrown
        if(company.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("company not found"));

        // if there is no complaints for this company in specific state locale, an exception is thrown
        List<Complaint> complaints = this.complaintRepository.findByCnpjCompanyState(cnpjCompany, state);
        if(complaints.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("no complaints was found for this company in this state"));
        // return all complaints found
        return complaints;
    }

    /**
     * method to retrieve all complaints stored for specific company in a specific state and city locale.
     * In case there is no company found, an exceptions is thrown with message 'company not found'. 
     * In case there is no compaints, an exception is thrown
     * with message 'no complaints was found for this company'
     * 
     * @param cnpjCompany - represent a cnpj company
     * @param state - represent a state locale
     * @param city - represent a city locale
     */
    @Override
    public List<Complaint> findAllByCnpjCompanyStateCity(String cnpjCompany, String state, String city) {
        //searching a companies with cnpj passed
        List<Company> company = this.companyRepository.findByCnpj(cnpjCompany);
        //if there is no companies, an exception is thrown
        if(company.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("company not found"));
        
            // if there is no complaints for this company in specific state and city locale, an exception is thrown
        List<Complaint> complaints = this.complaintRepository.findByCnpjCompanyStateCity(cnpjCompany, state, city);
        if(complaints.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("no complaints was found for this company in this state"));
        // return all complaints found
        return complaints;
    }

    /**
     * method to retrieve all complaints stored in a specific state locale.
     * In case there is no complaint found, an exceptions is thrown with message 'no complaints was found in this state'. 
     * 
     * @param state - represent a state locale
     */
    @Override
    public List<Complaint> findAllByState(String state) {
        //searching a complaints by state locale
        List<Complaint> complaints = this.complaintRepository.findByState(state);
        //if there is no complaints, an exception is thrown
        if(complaints.isEmpty()) 
            Optional.empty().orElseThrow(() -> new NotFoundException("no complaints was found in this state"));
        // return all complaints found
        return complaints;
    }

    /**
     * method to retrieve all complaints stored in a specific state and city locale.
     * In case there is no compaints, an exception is thrown with message 'no complaints was found for this city'.
     * 
     * @param state - represent a state locale
     */
    @Override
    public List<Complaint> findAllByStatecity(String state, String city) {
        //searching a complaints by state and city locale
        List<Complaint> complaints = this.complaintRepository.findByStateCity(state, city);
        //if there is no complaints, an exception is thrown
        if(complaints.isEmpty()) 
            Optional.empty().orElseThrow(() -> new NotFoundException("no complaints was found in this city"));
        // return all complaints found
        return complaints;
    }

    /**
     * Method to save a new complaint in database.
     * 
     * @param complaintDto - represent a fields that user want saved
     */
    @Override
    public Complaint create(ComplaintDTO complainDto) {
        //searching a company by idCompany. If there is no company, an exception is thrown
        Company company = this.companyRepository.findById(complainDto.getIdCompany())
                .orElseThrow(() -> new NotFoundException("company doesnt exist"));

        // code block that convert complaintDto to complaint object and set a company
        Complaint complaint = fromDto(complainDto);
        complaint.setCompany(company);
        //save and return a new complaint
        return this.complaintRepository.save(complaint);
    }

    /**
     * method to update a specific complaint with new values passed by user. 
     * In case there is no found a complaint, an exception is thrown with message 'complaint doesnt exist'.
     * In case there is no found a company, an exception is thrown with message 'company doesnt exist'.
     * 
     * @param complaintDto - represent a fields with new values
     * @param id - represent primary key complaint
     */
    @Override
    public Complaint update(ComplaintDTO complaintDto, String id) {
        //searching a complaint by id. If there is no complaint, an exception is thrown
        this.complaintRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("complaint doesnt exist"));

        //searching a company by idCompany. If there is no company, an exception is thrown
        Company company = this.companyRepository.findById(complaintDto.getIdCompany())
                .orElseThrow(() -> new NotFoundException("company doesnt exist"));
        
                //code block that set primary key and company and convert complaintDto to complaint
        complaintDto.setId(id);
        Complaint complaint = fromDto(complaintDto);
        complaint.setCompany(company);
        //updated and return a complaint
        return this.complaintRepository.save(complaint);
    }

    /**
     * method to remove a specific complaint by primary key passed for user. If there is no found a complaint, 
     * an exception is throw
     * 
     * @param id - represent a primary key
     */
    @Override
    public void remove(String id) {
        //searching if a complaint exist. If there is no found, an exception is throw
        this.complaintRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("complaint doesnt exist"));
        //remove a complaint
        this.complaintRepository.deleteById(id);
    }

    /**
     * private method to convert a ComplaintDTO object to a Complaint object
     * 
     * @param complaintDto - represent a complaintDto object
     */
    private Complaint fromDto(ComplaintDTO complainDto) {
        return new Complaint(complainDto.getId(), 
                complainDto.getTitle(), complainDto.getDescription(), null);
    }
}