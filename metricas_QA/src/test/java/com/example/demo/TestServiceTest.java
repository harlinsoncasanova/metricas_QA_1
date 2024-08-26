package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.api.dto.request.TestCycleRequest;
import com.example.demo.api.dto.response.TestCycleDTO;
import com.example.demo.domain.entities.Application;
import com.example.demo.domain.entities.MetricEntity;
import com.example.demo.domain.entities.TestCycle;
import com.example.demo.domain.entities.Version;
import com.example.demo.domain.repositories.ApplicationRepository;
import com.example.demo.domain.repositories.TestCycleRepository;
import com.example.demo.domain.repositories.VersionRepository;
import com.example.demo.infraestructure.services.TestService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestServiceTest {

    @Mock
    private TestCycleRepository testCycleRepository;

    @Mock
    private VersionRepository versionRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private TestService testService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTestCycle() {
        TestCycleRequest request = new TestCycleRequest();
        request.setAplicacionEntity("Test App");
        request.setVersionName("1.0");
        request.setCycleName("Cycle 1");
        request.setCycleDescription("Test Cycle Description");

        Application application = new Application();
        application.setName("Test App");

        Version version = new Version();
        version.setVersionName("1.0");
        version.setApplication(application);

        when(applicationRepository.findByName("Test App")).thenReturn(Optional.of(application));
        when(versionRepository.findByVersionName("1.0")).thenReturn(Arrays.asList(version));

        TestCycle testCycle = new TestCycle();
        testCycle.setId(1L);
        testCycle.setCycleName("Cycle 1");
        testCycle.setCycleDescription("Test Cycle Description");
        testCycle.setVersion(version);

        when(testCycleRepository.save(any(TestCycle.class))).thenReturn(testCycle);

        TestCycleDTO result = testService.create(request);

        assertNotNull(result);
        assertEquals("Cycle 1", result.getCycleName());
        verify(testCycleRepository, times(1)).save(any(TestCycle.class));
    }

    @Test
    void testCalculateTestFailureRate() {
        MetricEntity executedMetric = new MetricEntity();
        executedMetric.setMetricName("Executed Test Cases");
        executedMetric.setMetricValue(10.0);

        MetricEntity failedMetric = new MetricEntity();
        failedMetric.setMetricName("Failed Test Cases");
        failedMetric.setMetricValue(2.0);

        List<MetricEntity> metrics = Arrays.asList(executedMetric, failedMetric);

        double failureRate = testService.calculateTestFailureRate(metrics);

        assertEquals(20.0, failureRate);
    }
}
