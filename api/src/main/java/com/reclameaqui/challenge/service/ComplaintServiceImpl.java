package com.reclameaqui.challenge.service;

import java.util.ArrayList;
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
    public List<Complaint> findAllByCompany(String company) {
        List<Complaint> complains = this.complaintRepository.findAllByCompany(company);
        if(complains.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("company not found"));
        return complains;
    }

    @Override
    public List<Complaint> findAllByCompanyLocale(String company, String locale) {
        List<Complaint> complains = this.complaintRepository.findByCompanyLocale(company, locale);
        if(complains.isEmpty())
            Optional.empty().orElseThrow(() -> new NotFoundException("company or city doesnt exist"));
        return complains;
    }

    @Override
    public List<Complaint> findAllByLocale(String locale) {
        List<Complaint> complains = this.complaintRepository.findByLocale(locale);
        if(complains.isEmpty()) 
            Optional.empty().orElseThrow(() -> new NotFoundException("city doesnt exist"));
        return complains;
    }

    @Override
    public Complaint create(ComplaintDTO complainDto) {
        Company company = this.companyRepository.findByCnpj(complainDto.getCnpj());
        if(company == null)
            Optional.empty().orElseThrow(() -> new NotFoundException("company doesnt exist"));

        Complaint complaint = fromDto(complainDto);
        complaint.setCompany(company);
        complaint = this.complaintRepository.save(complaint);
        
        List<Complaint> complaints = new ArrayList<Complaint>();
        complaints.add(complaint);
        company.setComplaints(complaints);
        this.companyRepository.save(company);
        
        return complaint;
    }

    @Override
    public Complaint update(ComplaintDTO complaintDto, String id) {
        this.complaintRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("complaint doesnt exist"));
        
        Company company = this.companyRepository.findByCnpj(complaintDto.getCnpj());
        if(company == null)
            Optional.empty().orElseThrow(() -> new NotFoundException("company doesnt exist"));

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