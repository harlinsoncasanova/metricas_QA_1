package com.example.demo.infraestructure.services;



import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.api.dto.request.VersionRequest;
import com.example.demo.api.dto.response.VersionDTO;
import com.example.demo.domain.entities.Application;
import com.example.demo.domain.entities.Version;
import com.example.demo.domain.repositories.ApplicationRepository;
import com.example.demo.domain.repositories.VersionRepository;
import com.example.demo.infraestructure.abstrac_service.IVersionService;



@Service
@AllArgsConstructor
public class VersionService implements IVersionService {
    @Autowired
    private final ApplicationRepository applicationRepository;
    @Autowired
    private final VersionRepository versionRepository;


    @Override
    public VersionDTO create(VersionRequest request) {
        Application application = applicationRepository.findByName(request.getName())
                .orElseThrow(() -> new EntityNotFoundException("Aplicación no encontrada"));
        Version versionEntity = new Version();
        versionEntity.setVersionName(request.getVersionName());
        versionEntity.setApplication(application);

        Version saved = versionRepository.save(versionEntity);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public VersionDTO get(Long id) {
        Version versionEntity = versionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Versión no encontrada con id: " + id));
        return toResponse(versionEntity);
    }

    @Override
    public VersionDTO update(VersionRequest request, Long id) {
        Version versionEntity = versionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Versión no encontrada con id: " + id));

        versionEntity.setVersionName(request.getVersionName());
        Version updated = versionRepository.save(versionEntity);
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!versionRepository.existsById(id)) {
            throw new EntityNotFoundException("Versión no encontrada con id: " + id);
        }
        versionRepository.deleteById(id);
    }

    private VersionDTO toResponse(Version versionEntity) {
        VersionDTO response = new VersionDTO();
        response.setId(Math.toIntExact(versionEntity.getId()));
        response.setVersionName(versionEntity.getVersionName());
        response.setNameApp(versionEntity.getApplication().getName());
        return response;
    }
}
