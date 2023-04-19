package com.qa.demo.rest;

import com.qa.demo.domain.Cat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatController {

    private List<Cat> cats = new ArrayList<>();

    @PostMapping("/create")
    public Cat createCat(@RequestBody Cat newCat) {

        this.cats.add(newCat);
        return this.cats.get(cats.size() - 1);
    }
    
    @GetMapping("/") 
    public String greeting() {
        return "Hello, World!";
    }
}
