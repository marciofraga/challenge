package com.reclameaqui.challenge.service;

import java.util.List;

import com.reclameaqui.challenge.dto.ComplaintDTO;
import com.reclameaqui.challenge.model.Complaint;

/** interface represent all methods where complaint service has */
public interface ComplaintService {
    
    List<Complaint> findAll();
    Complaint findById(String id);
    Complaint create(ComplaintDTO complain);
    Complaint update(ComplaintDTO complain, String id);
    void remove(String id);

    List<Complaint> findAllByCnpjCompany(String company);
    List<Complaint> findAllByCnpjCompanyState(String cnpjCompany, String state);
    List<Complaint> findAllByCnpjCompanyStateCity(String cnpjCompany, String state, String city);

    List<Complaint> findAllByState(String state);
    List<Complaint> findAllByStatecity(String state, String city);
}
