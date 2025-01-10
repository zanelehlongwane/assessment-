// Package declaration
package com.enviro.assessment.grad001.ZaneleHlongwane.controllers;

// Import statements for required classes
import com.enviro.assessment.grad001.ZaneleHlongwane.model.EnvironmentalData;
import com.enviro.assessment.grad001.ZaneleHlongwane.RepositoryInterface.EnvironmentalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Annotation to specify that this class is a REST controller
@RestController
// Annotation to specify the base URL for the endpoints in this controller
@RequestMapping(path="/api")
public class FileProcessingController {

    // Field to store the repository for EnvironmentalData
    private final EnvironmentalDataRepository environmentalDataRepository;

    // Constructor with dependency injection for the repository
    @Autowired
    public FileProcessingController(EnvironmentalDataRepository environmentalDataRepository) {
        this.environmentalDataRepository = environmentalDataRepository;
    }

    // Endpoint to handle file uploads
    @PostMapping(path="/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Check if the uploaded file is empty
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Please select a file to upload.");
            }

            // Check if the uploaded file is a text file
            if (!MediaType.TEXT_PLAIN.equals(MediaType.parseMediaType(Objects.requireNonNull(file.getContentType())))) {
                return ResponseEntity.badRequest().body("Please upload a text file.");
            }

            // Process the file and save environmental data to the database
            List<EnvironmentalData> dataList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    EnvironmentalData environmentalData = new EnvironmentalData();
                    environmentalData.setData(line);
                    dataList.add(environmentalData);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Save data to the database
            environmentalDataRepository.saveAll(dataList);

            return ResponseEntity.ok("File uploaded and processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process the file: " + e.getMessage());
        }
    }

    // Endpoint to retrieve all environmental data
    @GetMapping(path="/data")
    public ResponseEntity<List<EnvironmentalData>> getAllData() {
        List<EnvironmentalData> data = environmentalDataRepository.findAll();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
