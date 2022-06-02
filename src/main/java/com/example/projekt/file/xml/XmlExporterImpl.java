package com.example.projekt.file.xml;

import com.example.projekt.domain.CpuEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

@Service
@Slf4j
public class XmlExporterImpl implements XmlExporter {

    @Override
    public String jaxbObjectToXML(List<CpuEntity> cpuList) {
        try
        {
            Cpus cpuClass = new Cpus();
            cpuClass.setCpuEntities(cpuList);
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Cpus.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(cpuClass, sw);

            //Verify XML Content
            String xmlContent = sw.toString();
            System.out.println( xmlContent );
            return xmlContent;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cpus jaxbXmlToObject(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Cpus.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Cpus cpus = (Cpus) jaxbUnmarshaller.unmarshal(new StringReader(xml));

        return cpus;
    }
}
