package com.enviro.assessment.grad001.ZaneleHlongwane.controllers;

import com.enviro.assessment.grad001.ZaneleHlongwane.model.EnvironmentalData;
import com.enviro.assessment.grad001.ZaneleHlongwane.RepositoryInterface.EnvironmentalDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileProcessingController.class)
public class FileProcessingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvironmentalDataRepository environmentalDataRepository;

    private MockMultipartFile mockFile;

    @BeforeEach
    public void setup() {
        // Create a mock text file
        mockFile = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Sample environmental data".getBytes()
        );
    }

    @Test
    public void testHandleFileUpload() throws Exception {
        // Mock the repository saveAll method
        when(environmentalDataRepository.saveAll(anyList())).thenReturn(Collections.emptyList());

        // Perform a multipart file upload request
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/upload")
                        .file(mockFile))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("File uploaded and processed successfully.")));
    }

    @Test
    public void testGetAllData() throws Exception {
        // Create a sample EnvironmentalData object
        EnvironmentalData environmentalData = new EnvironmentalData("Sample environmental data");

        // Mock the repository findAll method
        when(environmentalDataRepository.findAll()).thenReturn(List.of(environmentalData));

        // Perform a GET request to retrieve all data
        mockMvc.perform(MockMvcRequestBuilders.get("/api/data"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'data':'Sample environmental data'}]"));
    }
}
