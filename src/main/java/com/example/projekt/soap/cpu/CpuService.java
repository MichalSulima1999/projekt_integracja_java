package com.example.projekt.soap.cpu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// TODO: Connect to DB

@Service
@Slf4j
public class CpuService {
    private List<Cpu> cpus;

    public Cpu getCpuById(long id) {
        log.info(cpus.toString());
        return cpus.stream()
                .filter(cpu -> cpu.getId() == id)
                .findFirst().get();
    }

    public void setCpus(List<Cpu> cpus) {
        this.cpus = cpus;
    }

    public CpuService() {
        cpus = new ArrayList<>();

        Cpu cpu1 = new Cpu();
        cpu1.setId(1);
        cpu1.setCores(2);
        cpu1.setBase_frequency(1000);
        cpu1.setLithography(14);
        cpu1.setLaunch_date(new Date());
        cpu1.setManufacturer("POLLUB");
        cpu1.setName("I6 10450");
        cpu1.setSocket("POLIBUDA50X");
        cpu1.setTdp(650);
        cpu1.setTurbo_frequency("14000");
        cpu1.setThreads(4);

        Cpu cpu2 = new Cpu();
        cpu2.setId(2);
        cpu2.setCores(4);
        cpu2.setBase_frequency(1500);
        cpu2.setLithography(7);
        cpu2.setLaunch_date(new Date());
        cpu2.setManufacturer("POLLUB");
        cpu2.setName("I8 10101");
        cpu2.setSocket("POLIBUDA50X1");
        cpu2.setTdp(650);
        cpu2.setTurbo_frequency("14005");
        cpu2.setThreads(8);

        cpus.add(cpu1);
        cpus.add(cpu2);
    }
}
