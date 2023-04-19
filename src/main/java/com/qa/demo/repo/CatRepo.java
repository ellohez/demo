package com.qa.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.demo.domain.Cat;

@Repository
public interface CatRepo extends JpaRepository<Cat, Long> {
    
    // Custom method - all other CRUD auto
    // Spring uses this to generate the DB query 
    // "Select * from Cat where name = "
    List<Cat> findByName(String name); 
}
