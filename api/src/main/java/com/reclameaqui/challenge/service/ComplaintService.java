package com.reclameaqui.challenge.service;

import java.util.List;

import com.reclameaqui.challenge.dto.ComplaintDTO;
import com.reclameaqui.challenge.model.Complaint;

public interface ComplaintService {
    
    List<Complaint> findAll();
    List<Complaint> findAllByLocale(String locale);
    List<Complaint> findAllByCompany(String company);
    List<Complaint> findAllByCompanyLocale(String company, String locale);
    Complaint findById(String id);
    Complaint create(ComplaintDTO complain);
    Complaint update(ComplaintDTO complain, String id);
    void remove(String id);
}
