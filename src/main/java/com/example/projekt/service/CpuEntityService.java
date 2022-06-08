package com.example.projekt.service;

import com.example.projekt.domain.CpuEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CpuEntityService {
    CpuEntity getEntityById(long id);
    CpuEntity getEntityByName(String name);
    List<CpuEntity> getAllEntities();
    List<CpuEntity> getAllEntitiesSortedByDate();
    Page<CpuEntity> getAllEntitiesWithPagination(int offset, int pageSize);
    CpuEntity addEntity(CpuEntity entity);
    List<CpuEntity> addEntities(List<CpuEntity> cpuEntities);
    boolean updateEntity(CpuEntity entity);
    boolean deleteEntity(long id);
    boolean deleteEntities(List<Long> ids);
}
