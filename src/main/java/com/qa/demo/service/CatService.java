package com.qa.demo.service;
import java.util.List;

import com.qa.demo.domain.Cat;

public interface CatService {
    
    // All methods are public and abstract by default
    Cat createCat(cat c);
    Cat getByID(int id);
    Cat updateCat(Cat cat);
    Cat deleteCat(int id);
    List<Cat> getAll();
    Cat updateCat(String name, Boolean hasWhiskers, Boolean evil, Double length);
}
