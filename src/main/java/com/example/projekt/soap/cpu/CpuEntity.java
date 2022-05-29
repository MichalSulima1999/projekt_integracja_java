package com.example.projekt.soap.cpu;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cpu")
public class CpuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    private int cores;
    private int threads;
    private String name;
    private Date launch_date;
    protected int lithography;
    protected int base_frequency;
    protected String turbo_frequency;
    protected int tdp;
    protected String socket;
    protected String manufacturer;

    public CpuEntity(int cores, int threads, String name, Date launch_date, int lithography, int base_frequency, String turbo_frequency, int tdp, String socket, String manufacturer) {
        this.cores = cores;
        this.threads = threads;
        this.name = name;
        this.launch_date = launch_date;
        this.lithography = lithography;
        this.base_frequency = base_frequency;
        this.turbo_frequency = turbo_frequency;
        this.tdp = tdp;
        this.socket = socket;
        this.manufacturer = manufacturer;
    }

    public CpuEntity(int cores, int threads, String name, Date launch_date, int lithography, int base_frequency, int tdp, String socket, String manufacturer) {
        this.cores = cores;
        this.threads = threads;
        this.name = name;
        this.launch_date = launch_date;
        this.lithography = lithography;
        this.base_frequency = base_frequency;
        this.tdp = tdp;
        this.socket = socket;
        this.manufacturer = manufacturer;
    }
}
