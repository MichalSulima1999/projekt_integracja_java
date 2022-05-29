package com.example.projekt.soap.cpu;

import java.util.List;

public interface CpuEntityService {
    public CpuEntity getEntityById(long id);
    public CpuEntity getEntityByName(String name);
    public List<CpuEntity> getAllEntities();
    public CpuEntity addEntity(CpuEntity entity);
    public boolean updateEntity(CpuEntity entity);
    public boolean deleteEntity(long id);
}
