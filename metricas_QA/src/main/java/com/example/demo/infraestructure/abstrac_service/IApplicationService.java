package com.example.demo.infraestructure.abstrac_service;

import java.util.List;

import com.example.demo.api.dto.request.AplicationRequest;
import com.example.demo.api.dto.response.ApplicationDTO;
import com.example.demo.api.dto.response.TestCycleDTO;

public interface IApplicationService
extends CrudGeneral<AplicationRequest, ApplicationDTO,Long>{
    public ApplicationDTO getById(Long id);

    List<TestCycleDTO> getTestCyclesByApplicationNameAndVersion(String name, String versionName);
}