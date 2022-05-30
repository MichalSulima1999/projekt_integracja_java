package com.example.projekt.file;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="procesory")
@XmlAccessorType(XmlAccessType.FIELD)
public class Procesory {
    @XmlElement(name="procesor")
    private List<Procesor> procesory = null;

    public List<Procesor> getProcesory() {
        return procesory;
    }

    public void setProcesory(List<Procesor> procesory) {
        this.procesory = procesory;
    }
}

