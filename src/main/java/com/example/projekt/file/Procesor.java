package com.example.projekt.file;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@XmlRootElement(name="procesor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Procesor {

    @XmlTransient
    private int id;
    private int cores;
    private int threads;
    private String name;
    private Date launch_date;
    private int lithography;
    private int frequency;
    private int turbo_frequency;
    private int tdp;
    private String socket;
    private String manufacturer;

    public Procesor(){}

    public Procesor(int id, int cores, int threads, String name, Date launch_date, int lithography, int frequency, int turbo_frequency, int tdp, String socket, String manufacturer) {
        this.id = id;
        this.cores = cores;
        this.threads = threads;
        this.name = name;
        this.launch_date = launch_date;
        this.lithography = lithography;
        this.frequency = frequency;
        this.turbo_frequency = turbo_frequency;
        this.tdp = tdp;
        this.socket = socket;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Date launch_date) {
        this.launch_date = launch_date;
    }

    public int getLithography() {
        return lithography;
    }

    public void setLithography(int lithography) {
        this.lithography = lithography;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getTurbo_frequency() {
        return turbo_frequency;
    }

    public void setTurbo_frequency(int turbo_frequency) {
        this.turbo_frequency = turbo_frequency;
    }

    public int getTdp() {
        return tdp;
    }

    public void setTdp(int tdp) {
        this.tdp = tdp;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString(){
        return "Processor [id="+id
                +", cores="+cores
                +", threads="+threads
                +", name="+name
                +", launch_date="+launch_date
                +", lithography="+lithography
                +", frequency="+frequency
                +", turbo_frequency="+turbo_frequency
                +", tdp="+tdp
                +", socket="+socket
                +", manufacturer="+manufacturer+"]";
    }

}
