package com.example.demo.infraestructure.abstrac_service;

import com.example.demo.api.dto.request.TestCycleRequest;
import com.example.demo.api.dto.response.TestCycleDTO;

public interface ITestService extends  CrudGeneral<TestCycleRequest, TestCycleDTO,Long>{
}
