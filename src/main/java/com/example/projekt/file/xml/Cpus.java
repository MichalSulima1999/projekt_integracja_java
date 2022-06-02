package com.example.projekt.file.xml;

import com.example.projekt.domain.CpuEntity;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cpus")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Cpus {
    @XmlElement(name = "cpu")
    private List<CpuEntity> cpuEntities = null;
}
