package com.example.projekt.file.json;

import com.example.projekt.domain.CpuEntity;

import java.util.List;

public interface JsonExporter {
    String export(List<CpuEntity> cpus);
    List<CpuEntity> importJson(String json);
}
