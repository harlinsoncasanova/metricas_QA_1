package com.example.demo.domain.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.Application;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {
    @EntityGraph(attributePaths = {"versions.testCycles.metrics"})
    Optional<Application> findByName(String name);

    @EntityGraph(attributePaths = {"versions.testCycles.metrics"})
    Optional<Application> findById(Long id);
}
