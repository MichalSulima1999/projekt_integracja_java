package com.example.projekt.service;

import com.example.projekt.domain.CpuEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CpuEntityService {
    public CpuEntity getEntityById(long id);
    public CpuEntity getEntityByName(String name);
    public List<CpuEntity> getAllEntities();
    public List<CpuEntity> getAllEntitiesSortedByDate();
    public Page<CpuEntity> getAllEntitiesWithPagination(int offset, int pageSize);
    public CpuEntity addEntity(CpuEntity entity);
    public List<CpuEntity> addEntities(List<CpuEntity> cpuEntities);
    public boolean updateEntity(CpuEntity entity);
    public boolean deleteEntity(long id);
    public boolean deleteEntities(List<Long> ids);
}
