// Package declaration
package com.enviro.assessment.grad001.ZaneleHlongwane.RepositoryInterface;

// Import statements for required classes
import com.enviro.assessment.grad001.ZaneleHlongwane.model.EnvironmentalData;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface declaration
// This interface extends JpaRepository which provides JPA related methods for standard data access operations
public interface EnvironmentalDataRepository extends JpaRepository<EnvironmentalData, Long> {

    // No additional methods are needed as JpaRepository provides standard methods
    // such as save(), findAll(), findById(), deleteById(), etc.
}
