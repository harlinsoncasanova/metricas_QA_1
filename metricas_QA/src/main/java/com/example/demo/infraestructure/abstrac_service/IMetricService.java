package com.example.demo.infraestructure.abstrac_service;

import com.example.demo.api.dto.request.MetricRequest;
import com.example.demo.api.dto.response.MetricDTO;

public interface IMetricService extends CrudGeneral<MetricRequest, MetricDTO,Long>{
}