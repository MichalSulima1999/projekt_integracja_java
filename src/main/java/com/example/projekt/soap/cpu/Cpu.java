package com.example.projekt.soap.cpu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;


/**
 * <p>Java class for pl.bykowski.springbootsoapexample.student complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="pl.bykowski.springbootsoapexample.student">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cpu", namespace = "http://integracja.pl/soap", propOrder = {
        "id",
        "cores",
        "threads",
        "name",
        "launch_date",
        "lithography",
        "base_frequency",
        "turbo_frequency",
        "tdp",
        "socket",
        "manufacturer"
})
public class Cpu {

    protected long id;
    @XmlElement(required = true)
    protected int cores;
    @XmlElement(required = true)
    protected int threads;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected Date launch_date;
    @XmlElement(required = true)
    protected int lithography;
    @XmlElement(required = true)
    protected int base_frequency;
    @XmlElement()
    protected String turbo_frequency;
    @XmlElement(required = true)
    protected int tdp;
    @XmlElement(required = true)
    protected String socket;
    @XmlElement(required = true)
    protected String manufacturer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param name
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link Date }
     *
     */
    public Date getLaunch_date() {
        return launch_date;
    }

    /**
     * Sets the value of the name property.
     *
     * @param launch_date
     *     allowed object is
     *     {@link Date }
     *
     */
    public void setLaunch_date(Date launch_date) {
        this.launch_date = launch_date;
    }

    public int getLithography() {
        return lithography;
    }

    public void setLithography(int lithography) {
        this.lithography = lithography;
    }

    public int getBase_frequency() {
        return base_frequency;
    }

    public void setBase_frequency(int base_frequency) {
        this.base_frequency = base_frequency;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTurbo_frequency() {
        return turbo_frequency;
    }

    /**
     * Sets the value of the name property.
     *
     * @param turbo_frequency
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTurbo_frequency(String turbo_frequency) {
        this.turbo_frequency = turbo_frequency;
    }

    public int getTdp() {
        return tdp;
    }

    public void setTdp(int tdp) {
        this.tdp = tdp;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSocket() {
        return socket;
    }

    /**
     * Sets the value of the name property.
     *
     * @param socket
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSocket(String socket) {
        this.socket = socket;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the value of the name property.
     *
     * @param manufacturer
     *     allowed object is
     *     {@link String }
     *
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}