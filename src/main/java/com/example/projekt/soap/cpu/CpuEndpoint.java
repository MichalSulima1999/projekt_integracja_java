package com.example.projekt.soap.cpu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Slf4j
public class CpuEndpoint {

    private CpuService cpuService;

    public CpuEndpoint(CpuService cpuService) {
        this.cpuService = cpuService;
    }

    @PayloadRoot(namespace = "http://integracja.pl/soap", localPart = "getCpu")
    @ResponsePayload
    public GetResponse getCpuById(@RequestPayload GetCpu getCpu){
        Cpu cpu = cpuService.getCpuById(getCpu.getId());
        GetResponse getResponse = new GetResponse();
        getResponse.setCpu(cpu);
        log.info(cpu.toString());
        log.info(getResponse.toString());
        return getResponse;
    }
}
