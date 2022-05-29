package com.example.projekt.soap.cpu;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuEntityRepository extends CrudRepository<CpuEntity, Long> {
    public CpuEntity findById(long id);
    public CpuEntity findByName(String name);
    public CpuEntity findByManufacturer(String manufacturer);
}
