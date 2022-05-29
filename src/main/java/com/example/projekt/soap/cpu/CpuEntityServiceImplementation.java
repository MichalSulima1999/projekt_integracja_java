package com.example.projekt.soap.cpu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
    public CpuEntity addEntity(CpuEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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
}
