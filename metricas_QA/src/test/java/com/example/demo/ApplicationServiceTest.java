package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.api.dto.response.ApplicationDTO;
import com.example.demo.domain.entities.Application;
import com.example.demo.domain.repositories.ApplicationRepository;
import com.example.demo.infraestructure.services.ApplicationService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetApplicationById() {
        Application application = new Application();
        application.setId(1L);
        application.setName("Test App");

        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));

        ApplicationDTO result = applicationService.get(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test App", result.getName());
        verify(applicationRepository, times(1)).findById(1L);
    }
}

