package com.qa.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.demo.domain.Cat;

// Chooses a random port, as some ports are too popular
// This will find a free one to use. 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// Look in resources for the .sql files and run prior to tests
@Sql(scripts = {"classpath:cat-schema.sql", "classpath:cat-data.sql"})
public class CatIntegrationTest {
     
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception{

        Cat myCat = new Cat("Cindy Clawford", true, true, 10);
        String myJsonCat = this.mapper.writeValueAsString(myCat);

        // URL, method (POST), body JSON
        RequestBuilder req = MockMvcRequestBuilders.post("/create").content(myJsonCat).contentType(MediaType.APPLICATION_JSON);

        // Create matcher to use in request
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
        Cat created = new Cat(2L, "Cindy Clawford", true, true, 10);
        String createdAsJson = this.mapper.writeValueAsString(created);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(createdAsJson);
        
        // Send request
        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }
}
