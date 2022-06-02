package com.example.projekt.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cpu")
@XmlRootElement
public class CpuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int cores;
    private int threads;
    private String name;
    @Column(name = "launch_date")
    private Date launchDate;
    private int lithography;
    @Column(name = "base_frequency")
    private int baseFrequency;
    @Column(name = "turbo_frequency")
    private String turboFrequency;
    private int tdp;
    @Column(length = 11)
    private String socket;
    private String manufacturer;

    public CpuEntity(int cores, int threads, String name, Date launch_date, int lithography, int base_frequency, String turbo_frequency, int tdp, String socket, String manufacturer) {
        this.cores = cores;
        this.threads = threads;
        this.name = name;
        this.launchDate = launch_date;
        this.lithography = lithography;
        this.baseFrequency = base_frequency;
        this.turboFrequency = turbo_frequency;
        this.tdp = tdp;
        this.socket = socket;
        this.manufacturer = manufacturer;
    }

    public CpuEntity(int cores, int threads, String name, Date launch_date, int lithography, int base_frequency, int tdp, String socket, String manufacturer) {
        this.cores = cores;
        this.threads = threads;
        this.name = name;
        this.launchDate = launch_date;
        this.lithography = lithography;
        this.baseFrequency = base_frequency;
        this.tdp = tdp;
        this.socket = socket;
        this.manufacturer = manufacturer;
    }
}
