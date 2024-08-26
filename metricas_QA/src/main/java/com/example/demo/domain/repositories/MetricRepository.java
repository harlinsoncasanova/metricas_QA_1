package com.example.demo.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.MetricEntity;


@Repository
public interface MetricRepository  extends JpaRepository<MetricEntity, Long> {
}
