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

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Complaint> findAll() {
        return this.complaintRepository.findAll();
    }

    @Override
    public Complaint findById(String id) {
        return this.complaintRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("complaint not found"));
    }

    @Override
    public List<Complaint> findAllByCnpjCompany(String cnpjCompany) {
        List<Company> company = this.companyRepository.findByCnpj(cnpjCompany);
        if(company.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("company not found"));

        List<Complaint> complains = this.complaintRepository.findAllByCnpjCompany(cnpjCompany);
        if(complains.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("no complaints was found for this company"));

        return complains;
    }

    @Override
    public List<Complaint> findAllByCnpjCompanyState(String cnpjCompany, String state) {
        List<Company> company = this.companyRepository.findByCnpj(cnpjCompany);
        if(company.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("company not found"));

        List<Complaint> complaints = this.complaintRepository.findByCnpjCompanyState(cnpjCompany, state);
        if(complaints.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("no complaints was found for this company in this state"));
        return complaints;
    }

    @Override
    public List<Complaint> findAllByCnpjCompanyStateCity(String cnpjCompany, String state, String city) {
        List<Company> company = this.companyRepository.findByCnpj(cnpjCompany);
        if(company.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("company not found"));
        
        List<Complaint> complaints = this.complaintRepository.findByCnpjCompanyStateCity(cnpjCompany, state, city);
        if(complaints.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("no complaints was found for this company in this state"));
        return complaints;
    }

    @Override
    public List<Complaint> findAllByState(String state) {
        List<Complaint> complaints = this.complaintRepository.findByState(state);
        if(complaints.isEmpty()) 
            Optional.empty().orElseThrow(() -> new NotFoundException("no complaints was found in this state"));
        return complaints;
    }

    @Override
    public List<Complaint> findAllByStatecity(String state, String city) {
        List<Complaint> complaints = this.complaintRepository.findByStateCity(state, city);
        if(complaints.isEmpty()) 
            Optional.empty().orElseThrow(() -> new NotFoundException("no complaints was found in this city"));
        return complaints;
    }

    @Override
    public Complaint create(ComplaintDTO complainDto) {
        Company company = this.companyRepository.findById(complainDto.getIdCompany())
                .orElseThrow(() -> new NotFoundException("company doesnt exist"));

        Complaint complaint = fromDto(complainDto);
        complaint.setCompany(company);
        return this.complaintRepository.save(complaint);
    }

    @Override
    public Complaint update(ComplaintDTO complaintDto, String id) {
        this.complaintRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("complaint doesnt exist"));
        
        Company company = this.companyRepository.findById(complaintDto.getIdCompany())
                .orElseThrow(() -> new NotFoundException("company doesnt exist"));

        complaintDto.setId(id);
        Complaint complaint = fromDto(complaintDto);
        complaint.setCompany(company);
        return this.complaintRepository.save(complaint);
    }

    @Override
    public void remove(String id) {
        this.complaintRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("complain doesnt exist"));

        this.complaintRepository.deleteById(id);
    }

    private Complaint fromDto(ComplaintDTO complainDto) {
        return new Complaint(complainDto.getId(), 
                complainDto.getTitle(), complainDto.getDescription(), null);
    }
}