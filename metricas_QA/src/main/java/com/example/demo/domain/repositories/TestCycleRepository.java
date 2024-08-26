package com.example.demo.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.TestCycle;


@Repository
public interface TestCycleRepository extends JpaRepository<TestCycle, Long> {
}
