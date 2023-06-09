package com.qa.demo.service;
import java.util.List;

import com.qa.demo.domain.Cat;

public interface CatService {
    
    // All methods are public and abstract by default
    Cat createCat(Cat c);
    Cat getByID(int id);
    Cat deleteCat(int id);
    List<Cat> getAll();
    Cat updateCat(int id, String name, Boolean hasWhiskers, Boolean evil, Double length);
}
