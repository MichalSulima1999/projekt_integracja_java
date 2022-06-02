package com.example.projekt.file.json;

import com.example.projekt.domain.CpuEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonExporterImpl implements JsonExporter {

    @Override
    public String export(List<CpuEntity> cpus) {
        Gson gson = new Gson();
        String cpuInJson = gson.toJson(cpus);
        return cpuInJson;
    }

    @Override
    public List<CpuEntity> importJson(String json) {
        Gson gson = new Gson();
        Type listOfMyClassObject = new TypeToken<ArrayList<CpuEntity>>() {}.getType();
        List<CpuEntity> cpus = gson.fromJson(json, listOfMyClassObject);
        return cpus;
    }
}
