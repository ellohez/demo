package com.qa.demo.rest;

import com.qa.demo.domain.Cat;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.websocket.server.PathParam;

import java.util.ArrayList;
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

@RestController
public class CatController {

    private List<Cat> cats = new ArrayList<>();

    // No variables
    @PostMapping("/create")
    public ResponseEntity<Cat> createCat(@RequestBody Cat newCat) {

        cats.add(newCat);
        Cat createdCat = cats.get(cats.size() - 1);

        return new ResponseEntity<>(createdCat, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public Cat updateCat(@PathVariable int id, 
            @RequestParam(name="name", required=false) String name,
            @RequestParam(name="hasWhiskers", required=false) Boolean hasWhiskers,
            @RequestParam(name="evil", required=false) Boolean evil,
            @RequestParam(name="length", required=false) Double length) {

        Cat catChange = cats.get(id);
        if(name != null) catChange.setName(name); ;
        if(hasWhiskers != null) catChange.setHasWhiskers(hasWhiskers);
        if(evil != null) catChange.setEvil(evil);
        if(length != null) catChange.setLength(length);

        return catChange;
    }

    // ID sent as path variable (not query)
    @DeleteMapping("/remove/{id}")
    public Cat deleteCat(@PathVariable int id) {
        return this.cats.remove(id);
    }

    // ID sent as path variable (not query)
    @GetMapping("/get/{id}")
    public Cat get(@PathVariable int id) {
        return this.cats.get(id);
    }

    @GetMapping("/getAll")
    public List<Cat> getAll() {
        return this.cats;
    }
    
    // Test method - browsers will run this initially
    // Open localhost:8080 to check API is running
    @GetMapping("/") 
    public String greeting() {
        return "Hello, World!";
    }
}
