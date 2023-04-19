package com.qa.demo.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.demo.domain.Cat;
import com.qa.demo.exception.CatNotFoundException;
import com.qa.demo.repo.CatRepo;

@Primary
@Service
public class CatServiceDB implements CatService{

    private CatRepo catRepo;

    // Service is dependant on repo and Spring will inject it
    public CatServiceDB(CatRepo catRepo) {
        this.catRepo = catRepo;
    }

    @Override
    public Cat createCat(Cat c) {
        return this.catRepo.save(c);
    }

    @Override
    public List<Cat> getAll() {
        return this.catRepo.findAll();
    }

    @Override
    public Cat getByID(int id) {
        return this.catRepo.findById((long)id).orElseThrow(CatNotFoundException::new);
    }

    @Override
    public Cat deleteCat(int id) {

        Cat catRemoved = this.getByID(id);
        this.catRepo.deleteById((long) id);
        return catRemoved;
    }

    @Override
    public Cat updateCat(int id, String name, Boolean hasWhiskers, Boolean evil, Double length) {
        
        Cat c = this.getByID(id);
        if(name != null) c.setName(name);
        if(hasWhiskers != null) c.setHasWhiskers(hasWhiskers);;
        if(evil != null) c.setEvil(evil);
        if(length != null) c.setLength(length);

        return this.catRepo.save(c);
    }
    
}
