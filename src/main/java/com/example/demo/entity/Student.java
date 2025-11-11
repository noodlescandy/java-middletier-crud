package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; // Primary key

    private String name;
    // does this need other information? Requirements don't specify, so keeping it simple.
    // Adding things like GPA would be simpler than adding a whole new entity for courses/enrollments.

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
