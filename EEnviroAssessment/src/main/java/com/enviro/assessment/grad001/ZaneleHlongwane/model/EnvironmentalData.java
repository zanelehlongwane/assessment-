// Package declaration
package com.enviro.assessment.grad001.ZaneleHlongwane.model;

// Import statements for JPA annotations
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Annotation to specify that this class is an entity and is mapped to a database table
@Entity
public class EnvironmentalData {

    // Specifies the primary key of the entity
    @Id
    // Specifies the generation strategy for the primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Field to store the environmental data
    private String data;

    // Default constructor required by JPA
    public EnvironmentalData() {
    }

    // Parameterized constructor to initialize the data field
    public EnvironmentalData(String data) {
        this.data = data;
    }

    // Getter method for the id field
    public Long getId() {
        return id;
    }

    // Setter method for the id field
    public void setId(Long id) {
        this.id = id;
    }

    // Getter method for the data field
    public String getData() {
        return data;
    }

    // Setter method for the data field
    public void setData(String data) {
        this.data = data;
    }

}
