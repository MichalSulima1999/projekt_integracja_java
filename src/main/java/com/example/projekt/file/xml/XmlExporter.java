package com.example.projekt.file.xml;

import com.example.projekt.domain.CpuEntity;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface XmlExporter {
    String jaxbObjectToXML(List<CpuEntity> cpuList);
    Cpus jaxbXmlToObject(String xml) throws JAXBException;
}
