package com.qa.demo.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qa.demo.domain.Cat;

@Service
public class CatServiceList implements CatService {

    private List<Cat> cats = new ArrayList<>();

    @Override
    public Cat createCat(Cat c) {
        cats.add(c);
        Cat createdCat = cats.get(cats.size() - 1);

        return createdCat;
    }

    @Override
    public Cat deleteCat(int id) {
        return this.cats.remove(id);
    }

    @Override
    public List<Cat> getAll() {
        return this.cats;
    }

    @Override
    public Cat getByID(int id) {
        return this.cats.get(id);
    }

    @Override
    public Cat updateCat(int id, String name, Boolean hasWhiskers, Boolean evil, Double length) {
        Cat catChange = cats.get(id);
        if(name != null) catChange.setName(name); ;
        if(hasWhiskers != null) catChange.setHasWhiskers(hasWhiskers);
        if(evil != null) catChange.setEvil(evil);
        if(length != null) catChange.setLength(length);

        return catChange;
    }
    
}