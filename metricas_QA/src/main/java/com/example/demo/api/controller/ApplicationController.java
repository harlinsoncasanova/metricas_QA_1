package com.example.demo.api.controller;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.api.dto.request.AplicationRequest;
import com.example.demo.api.dto.request.ApplicationVersionRequest;
import com.example.demo.api.dto.response.ApplicationDTO;
import com.example.demo.api.dto.response.TestCycleDTO;
import com.example.demo.infraestructure.abstrac_service.IApplicationService;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/application")
@AllArgsConstructor

public class ApplicationController {
	
	 @Autowired
	    private final IApplicationService applicationService;

	    @PostMapping
	    public ResponseEntity<ApplicationDTO>create(
	            @Validated @RequestBody AplicationRequest applicationRequest){
	        return ResponseEntity.ok(this.applicationService.create(applicationRequest));

	    }
	    @GetMapping(path = "/{id}")
	    public ResponseEntity<ApplicationDTO>get(@PathVariable Long id){
	        return ResponseEntity.ok(this.applicationService.get(id));
	    }
	    @DeleteMapping(path = "/{id}")
	    public ResponseEntity<String> delete(@PathVariable Long id) {
	        this.applicationService.delete(id);

	        return ResponseEntity.ok("Eliminado exitosamente");
	    }
	    @PostMapping("/testCyclesByVersion")
	    public ResponseEntity<List<TestCycleDTO>> getTestCyclesByApplicationAndVersion(
	            @RequestBody ApplicationVersionRequest request) {
	        List<TestCycleDTO> testCycles = applicationService.getTestCyclesByApplicationNameAndVersion(
	                request.getName(), request.getVersionName());


	        testCycles.sort(Comparator.comparing(TestCycleDTO::getId));

	        if (testCycles.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        }

	        return ResponseEntity.ok(testCycles);
	    }

}
