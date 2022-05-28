package com.example.projekt.soap.cpu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "cpu"
})
@XmlRootElement(name = "getResponse", namespace = "http://integracja.pl/soap")
public class GetResponse {

    @XmlElement(required = true)
    protected Cpu cpu;

    /**
     * Gets the value of the cpu property.
     *
     * @return
     *     possible object is
     *     {@link Cpu }
     *
     */
    public Cpu getCpu() {
        return cpu;
    }

    /**
     * Sets the value of the cpu property.
     *
     * @param value
     *     allowed object is
     *     {@link Cpu }
     *
     */
    public void setCpu(Cpu value) {
        this.cpu = value;
    }

}