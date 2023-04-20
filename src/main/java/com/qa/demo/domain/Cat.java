package com.qa.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cat {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean hasWhiskers;
    private boolean evil;
    private double length;

    // Default constructor - Framework will call this and then use the getters/setters
    public Cat() {
        
    }

    public Cat(String name, boolean hasWhiskers, boolean evil, double length) {
        this.name = name;
        this.hasWhiskers = hasWhiskers;
        this.evil = evil;
        this.length = length;
    }
    
    public Cat(Long id, String name, boolean hasWhiskers, boolean evil, double length) {
        this.id = id;
        this.name = name;
        this.hasWhiskers = hasWhiskers;
        this.evil = evil;
        this.length = length;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isHasWhiskers() {
        return hasWhiskers;
    }
    public void setHasWhiskers(boolean hasWhiskers) {
        this.hasWhiskers = hasWhiskers;
    }
    public boolean isEvil() {
        return evil;
    }
    public void setEvil(boolean evil) {
        this.evil = evil;
    }
    
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
