package com.example.projekt.repo;

import com.example.projekt.domain.CpuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CpuEntityRepository extends JpaRepository<CpuEntity, Long> {
    public CpuEntity findById(long id);
    public CpuEntity findByName(String name);
    public CpuEntity findByManufacturer(String manufacturer);
}
