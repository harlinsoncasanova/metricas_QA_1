package com.example.demo.infraestructure.abstrac_service;

import com.example.demo.api.dto.request.VersionRequest;
import com.example.demo.api.dto.response.VersionDTO;

public interface IVersionService extends
CrudGeneral<VersionRequest, VersionDTO,Long>{
}
