// Package declaration
package com.enviro.assessment.grad001.ZaneleHlongwane;

// Import statements for Spring Boot classes and annotations
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Annotation to specify the base packages to scan for JPA entities
@EntityScan(basePackages = {"com.enviro.assessment.grad001.zanelehlongwane.model"})
// Annotation to mark this class as a Spring Boot application
@SpringBootApplication
// Annotation to enable component scanning for Spring beans
@ComponentScan
// Annotation to enable JPA repositories
@EnableJpaRepositories
public class EnviroApiApplication {

    // Main method to run the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(EnviroApiApplication.class, args);
    }
}
