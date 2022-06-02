package com.example.projekt.service;

import com.example.projekt.domain.CpuEntity;
import com.example.projekt.repo.CpuEntityRepository;
import com.example.projekt.service.CpuEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CpuEntityServiceImplementation implements CpuEntityService {
    private CpuEntityRepository repository;

    @Autowired
    public CpuEntityServiceImplementation(CpuEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public CpuEntity getEntityById(long id) {
        return repository.findById(id);
    }

    @Override
    public CpuEntity getEntityByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<CpuEntity> getAllEntities() {
        List<CpuEntity> list = new ArrayList<>();
        repository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public List<CpuEntity> getAllEntitiesSortedByDate() {
        List<CpuEntity> list = new ArrayList<>();
        repository.findByOrderByLaunchDateAsc().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Page<CpuEntity> getAllEntitiesWithPagination(int offset, int pageSize) {
        Page<CpuEntity> cpuEntities = repository.findAll(PageRequest.of(offset, pageSize));
        return cpuEntities;
    }

    @Override
    public CpuEntity addEntity(CpuEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public List<CpuEntity> addEntities(List<CpuEntity> cpuEntities) {
        for (CpuEntity cpu :
                cpuEntities) {
            repository.save(cpu);
        }
        return cpuEntities;
    }

    @Transactional
    @Override
    public boolean updateEntity(CpuEntity entity) {
        try {
            repository.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEntity(long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean deleteEntities(List<Long> ids) {
        try {
            for (Long id :
                    ids) {
                repository.deleteById(id);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
