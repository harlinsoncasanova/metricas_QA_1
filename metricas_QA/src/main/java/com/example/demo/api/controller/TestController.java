package com.example.demo.api.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.dto.request.TestCycleRequest;
import com.example.demo.api.dto.response.TestCycleDTO;
import com.example.demo.domain.repositories.ApplicationRepository;
import com.example.demo.domain.repositories.VersionRepository;
import com.example.demo.infraestructure.abstrac_service.ITestService;

@RestController
@RequestMapping("/testCycle")
@RequiredArgsConstructor
public class TestController {
	private final ITestService testCycleService;
    private final VersionRepository versionRepository;
    private final ApplicationRepository applicationRepository;

    @PostMapping
    public ResponseEntity<TestCycleDTO> createTestCycle(@RequestBody TestCycleRequest request) {
        TestCycleDTO response = testCycleService.create(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
