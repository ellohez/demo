package com.qa.demo.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.domain.Cat;
import com.qa.demo.service.CatService;

@RestController
public class CatController {

    private CatService service;

    public CatController(CatService service) {
        this.service = service;
    }

    // No variables
    @PostMapping("/create")
    public ResponseEntity<Cat> createCat(@RequestBody Cat newCat) {

        Cat createdCat = this.service.createCat(newCat);
        return new ResponseEntity<>(createdCat, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public Cat updateCat(@PathVariable int id, 
            @RequestParam(name="name", required=false) String name,
            @RequestParam(name="hasWhiskers", required=false) Boolean hasWhiskers,
            @RequestParam(name="evil", required=false) Boolean evil,
            @RequestParam(name="length", required=false) Double length) {

       Cat changedCat = this.service.updateCat(id, name, hasWhiskers, evil, length);
       return changedCat;
    }

    // ID sent as path variable (not query)
    @DeleteMapping("/remove/{id}")
    public Cat deleteCat(@PathVariable int id) {

        return this.service.deleteCat(id);
    }

    // ID sent as path variable (not query)
    @GetMapping("/get/{id}")
    public Cat getByID(@PathVariable int id) {
        
        return this.service.getByID(id);
    }

    @GetMapping("/getAll")
    public List<Cat> getAll() {
        
        return this.service.getAll();
    }
    
    // Test method - browsers will run this initially
    // Open localhost:8080 to check API is running
    @GetMapping("/") 
    public String greeting() {
        return "Hello, World!";
    }
}
