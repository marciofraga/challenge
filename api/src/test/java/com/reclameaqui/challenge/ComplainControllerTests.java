package com.reclameaqui.challenge;

import com.jayway.jsonpath.JsonPath;
import com.reclameaqui.challenge.model.Complaint;
import com.reclameaqui.challenge.repository.ComplaintRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ComplainControllerTests extends ChallengeApplicationTests{
    
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ComplaintRepository complainRepository;

    @Test
    void createTest() throws Exception {
        String jsonBody = "{\"title\": \"test one\", \"description\": \"test description\", \"locale\": \"Fortaleza\", \"company\": \"Drogasil\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/complains")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonBody))
                            .andExpect(MockMvcResultMatchers.status().isCreated())
                            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                            .andReturn();
        
        this.complainRepository.deleteById(JsonPath.read(result.getResponse().getContentAsString(), "$.id"));
    }

    @Test
    void createTestFailed() throws Exception {
        String jsonBody = "{\"description\": \"test description\", \"locale\": \"Fortaleza\", \"company\": \"Drogasil\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/complains")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void updateTest() throws Exception {
        String jsonBody = "{\"title\": \"test one\", \"description\": \"test description\", \"locale\": \"Fortaleza\", \"company\": \"Drogasil\"}";
        Complaint complainCreated = new Complaint();// = new Complaint("update test", "update test description", "Goiania", "test company");
        complainCreated = this.complainRepository.save(complainCreated);

        mockMvc.perform(MockMvcRequestBuilders.put("/complains/" + complainCreated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        
        this.complainRepository.delete(complainCreated);
    }

    @Test
    void updateFailedNotFoundTest() throws Exception {
        String jsonBody = "{\"title\": \"test one\", \"description\": \"test description\", \"locale\": \"Fortaleza\", \"company\": \"Drogasil\"}";
        Complaint complainCreated = new Complaint(); //Complaint("update test", "update test description", "Goiania", "test company");
        complainCreated = this.complainRepository.save(complainCreated);
        
        mockMvc.perform(MockMvcRequestBuilders.put("/complains/5f6b8e043fa927")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        
        this.complainRepository.delete(complainCreated);
    }

    @Test
    void updateFailedUnprocessableEntityTest() throws Exception {
        String jsonBody = "{\"title\": \"test one\", \"locale\": \"Fortaleza\", \"company\": \"Drogasil\"}";
        Complaint complainCreated = new Complaint();//Complaint("update test", "update test description", "Goiania", "test company");
        complainCreated = this.complainRepository.save(complainCreated);

        mockMvc.perform(MockMvcRequestBuilders.put("/complains/" + complainCreated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        
        this.complainRepository.delete(complainCreated);
    }

    @Test
    void removeTest() throws Exception {
        Complaint complainCreated = new Complaint();// Complaint("remove test", "remove test description", "Goiania", "test company");
        complainCreated = this.complainRepository.save(complainCreated);

        mockMvc.perform(MockMvcRequestBuilders.delete("/complains/"+ complainCreated.getId()))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
        
        this.complainRepository.delete(complainCreated);
    }

    @Test
    void removeFailedTest() throws Exception {
        Complaint complainCreated = new Complaint(); //Complaint("remove test", "remove test description", "Goiania", "test company");
        complainCreated = this.complainRepository.save(complainCreated);

        mockMvc.perform(MockMvcRequestBuilders.delete("/complains/5f6b8e043fa927"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        this.complainRepository.delete(complainCreated);
    }

    @Test
    void findAllComplainTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/complains"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void findAllByIdTest() throws Exception {
        Complaint complainCreated = new Complaint(); //Complaint("test", "test description", "Goiania", "test company");
        complainCreated = this.complainRepository.save(complainCreated);

        mockMvc.perform(MockMvcRequestBuilders.get("/complains/" + complainCreated.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        
        this.complainRepository.delete(complainCreated);
    }

    @Test
    void findAllByIdFailedTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/complains/5f6b8e043fa927"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void findAllByLocaleTest() throws Exception {
        Complaint complainCreated = new Complaint(); //("test", "test description", "Goiania", "test company");
        complainCreated = this.complainRepository.save(complainCreated);

        mockMvc.perform(MockMvcRequestBuilders.get("/complains/locale/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
            
        this.complainRepository.delete(complainCreated);
    }

    @Test
    void findAllByLocaleFailedTest() throws Exception {
        Complaint complainCreated = new Complaint(); //("test", "test description", "Goiania", "test company");
        complainCreated = this.complainRepository.save(complainCreated);

        mockMvc.perform(MockMvcRequestBuilders.get("/complains/locale/salvador"))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        
        this.complainRepository.delete(complainCreated);
    }

    @Test
    void findAllByCompanyTest() throws Exception {
        Complaint complainCreated = new Complaint(); //("test", "test description", "Goiania", "company");
        complainCreated = this.complainRepository.save(complainCreated);

        mockMvc.perform(MockMvcRequestBuilders.get("/complains/company/" + complainCreated.getCompany()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        
        this.complainRepository.delete(complainCreated);
    }

    @Test
    void findAllByCompanyFailedTest() throws Exception {
        Complaint complainCreated = new Complaint(); //("test", "test description", "Goiania", "company");
        complainCreated = this.complainRepository.save(complainCreated);

        mockMvc.perform(MockMvcRequestBuilders.get("/complains/company/testCompany"))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        this.complainRepository.delete(complainCreated);
    }

    @Test
    void findAllByCompanyLocaleTest() throws Exception {
        Complaint complainCreated = new Complaint(); //("test", "test description", "Goiania", "company");
        complainCreated = this.complainRepository.save(complainCreated);

        mockMvc.perform(MockMvcRequestBuilders.get("/complains/company/" 
                        + complainCreated.getCompany()))// + "/" 
                       // + complainCreated.getLocale()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        
        this.complainRepository.delete(complainCreated);
    }

    @Test
    void findAllByCompanyLocaleFailedTest() throws Exception {
        Complaint complainCreated = new Complaint(); //new Complaint("test", "test description", "Goiania", "company");
        complainCreated = this.complainRepository.save(complainCreated);

        mockMvc.perform(MockMvcRequestBuilders.get("/complains/company/testCompany/" ))
                        //+ complainCreated.getLocale()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        this.complainRepository.delete(complainCreated);
    }

    @Test
    void findAllByCompanyLocaleFailedTest2() throws Exception {
        Complaint complainCreated = new Complaint(); //("test", "test description", "Goiania", "company");
        complainCreated = this.complainRepository.save(complainCreated);

        mockMvc.perform(MockMvcRequestBuilders.get("/complains/company/" 
                        + complainCreated.getCompany() + "/testLocale" ))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        
        this.complainRepository.delete(complainCreated);
    }
}
