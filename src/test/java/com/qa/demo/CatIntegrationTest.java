package com.qa.demo;

import java.util.ArrayList;
import java.util.List;

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
        
        // Send request - expect 201 status and the created cat with ID
        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetByID() throws Exception{
        // URL, method (GET), body JSON
        RequestBuilder req = MockMvcRequestBuilders.get("/get/1");
        Cat returned = new Cat(1L, "Salem", false, true, 10);
        String returnedAsJson = this.mapper.writeValueAsString(returned);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(returnedAsJson);
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();

        // Send request - and expect 200 status code and the cat (ID 1) from the DB schema
        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetAll() throws Exception {
        // URL, method (GET), body JSON
        RequestBuilder req = MockMvcRequestBuilders.get("/getAll");

        Cat arrayCat = new Cat(1L, "Salem", false, true, 10);
        List<Cat> cats = new ArrayList<Cat>();
        cats.add(arrayCat);

        String returnedAsJson = this.mapper.writeValueAsString(cats);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(returnedAsJson);
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();

        // Send request - and expect 200 status code and the cat (ID 1) from the DB schema
        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testDelete() throws Exception {
        // URL, method (GET), body JSON
        RequestBuilder req = MockMvcRequestBuilders.delete("/remove/1");

        Cat removedCat = new Cat(1L, "Salem", false, true, 10);
        String returnedAsJson = this.mapper.writeValueAsString(removedCat);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(returnedAsJson);
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();

         // Send request - and expect 200 status code and the cat (ID 1) from the DB schema
         this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testUpdate() throws Exception {
         // URL, method (PATCH), body JSON
         RequestBuilder req = MockMvcRequestBuilders
                                    .patch("/update/1")
                                    .param("name", "Top Cat")
                                    .param("evil", "false");

        Cat updatedCat = new Cat(1L, "Top Cat", false, false, 10);

        String returnedAsJson = this.mapper.writeValueAsString(updatedCat);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(returnedAsJson);
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();

        // Send request - and expect 200 status code and the
        // updated cat (new name and EVIl)
         this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }
}
