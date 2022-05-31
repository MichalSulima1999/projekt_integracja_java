package com.example.projekt.file;

import com.example.projekt.domain.CpuEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="procesory")
@XmlAccessorType(XmlAccessType.FIELD)
public class Procesory {
    @XmlElement(name="procesor")
    private List<CpuEntity> procesory = null;

    public List<CpuEntity> getProcesory() {
        return procesory;
    }

    public void setProcesory(List<CpuEntity> procesory) {
        this.procesory = procesory;
    }
}

