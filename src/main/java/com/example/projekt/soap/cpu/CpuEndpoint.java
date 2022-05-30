package com.example.projekt.soap.cpu;

import com.example.projekt.domain.CpuEntity;
import com.example.projekt.repo.CpuEntityRepository;
import com.example.projekt.service.CpuEntityService;
import com.example.projekt.soap.cpu.pl.integracja.soap.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

import static java.util.Arrays.stream;

@Endpoint
@Slf4j
public class CpuEndpoint {

    public static final String NAMESPACE_URI = "http://integracja.pl/soap";
    private CpuEntityService service;
    private CpuEntityRepository cpuEntityRepository;

    @Autowired
    public CpuEndpoint(CpuEntityService service, CpuEntityRepository cpuEntityRepository) {
        this.service = service;
        this.cpuEntityRepository = cpuEntityRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCpuByIdRequest")
    @ResponsePayload
    public GetCpuByIdResponse getCpuById(@RequestPayload GetCpuByIdRequest getCpuByIdRequest){
        CpuEntity cpuEntity = service.getEntityById(getCpuByIdRequest.getId());
        GetCpuByIdResponse getResponse = new GetCpuByIdResponse();
        Cpu cpu = new Cpu();

        BeanUtils.copyProperties(cpuEntity, cpu);
        getResponse.setCpu(cpu);
        return getResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCpusRequest")
    @ResponsePayload
    public GetAllCpusResponse getAllCpus(@RequestPayload GetAllCpusRequest request) throws DatatypeConfigurationException {
        log.info(request.toString());

        GetAllCpusResponse response = new GetAllCpusResponse();
        List<Cpu> cpuTypeList = new ArrayList<>();
        List<CpuEntity> cpuEntities = service.getAllEntities();
        //List<CpuEntity> cpuEntities = cpuEntityRepository.findAll(Sort.by(Sort.Direction.ASC, "launchDate"));
        for (CpuEntity entity : cpuEntities) {
            Cpu cpuType = new Cpu();
            BeanUtils.copyProperties(entity, cpuType);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(entity.getLaunchDate());
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            cpuType.setLaunchDate(date2);
            cpuType.setBaseFrequency(entity.getBase_frequency());
            cpuType.setTurboFrequency(entity.getTurbo_frequency());
            cpuTypeList.add(cpuType);
//            log.info(cpuType.toString());
            log.info(entity.toString());
        }
        response.getCpu().addAll(cpuTypeList);


        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCpusByDateRequest")
    @ResponsePayload
    public GetAllCpusByDateResponse getAllCpusByDate(@RequestPayload GetAllCpusByDateRequest request) throws DatatypeConfigurationException {
        log.info(request.toString());

        GetAllCpusByDateResponse response = new GetAllCpusByDateResponse();
        List<Cpu> cpuTypeList = new ArrayList<>();
        //List<CpuEntity> cpuEntities = service.getAllEntities();
        List<CpuEntity> cpuEntities = cpuEntityRepository.findAll(Sort.by(Sort.Direction.ASC, "launchDate"));
        for (CpuEntity entity : cpuEntities) {
            Cpu cpuType = new Cpu();
            BeanUtils.copyProperties(entity, cpuType);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(entity.getLaunchDate());
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            cpuType.setLaunchDate(date2);
            cpuType.setBaseFrequency(entity.getBase_frequency());
            cpuType.setTurboFrequency(entity.getTurbo_frequency());
            cpuTypeList.add(cpuType);
            log.info(entity.toString());
        }
        response.getCpu().addAll(cpuTypeList);


        return response;

    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCpuRequest")
//    @ResponsePayload
//    public AddCpuResponse addCpu(@RequestPayload AddCpuRequest request) {
//        AddCpuResponse response = new AddCpuResponse();
//        Cpu newCpuType = new Cpu();
//        ServiceStatus serviceStatus = new ServiceStatus();
//
//        CpuEntity newCpuEntity = new CpuEntity(request.getCores(), request.getThreads(), request.getName(), request.getLaunchDate().toGregorianCalendar().getTime(), request.getLithography(), request.getBaseFrequency(), request.getTurboFrequency(), request.getTdp(), request.getSocket(), request.getManufacturer());
//        CpuEntity savedMovieEntity = service.addEntity(newCpuEntity);
//
//        if (savedMovieEntity == null) {
//            serviceStatus.setStatusCode("CONFLICT");
//            serviceStatus.setMessage("Exception while adding Entity");
//        } else {
//
//            BeanUtils.copyProperties(savedMovieEntity, newCpuType);
//            serviceStatus.setStatusCode("SUCCESS");
//            serviceStatus.setMessage("Content Added Successfully");
//        }
//
//        response.setCpu(newCpuType);
//        response.setServiceStatus(serviceStatus);
//        return response;
//
//    }
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCpuRequest")
//    @ResponsePayload
//    public UpdateCpuResponse updateCpu(@RequestPayload UpdateCpuRequest request) {
//        UpdateCpuResponse response = new UpdateCpuResponse();
//        ServiceStatus serviceStatus = new ServiceStatus();
//        // 1. Find if cpu available
//        CpuEntity cpuFromDB = service.getEntityByName(request.getName());
//
//        if(cpuFromDB == null) {
//            serviceStatus.setStatusCode("NOT FOUND");
//            serviceStatus.setMessage("Movie = " + request.getName() + " not found");
//        }else {
//
//            // 2. Get updated movie information from the request
//            cpuFromDB.setName(request.getName());
//            cpuFromDB.setCores(request.getCores());
//            cpuFromDB.setLithography(request.getLithography());
//            cpuFromDB.setManufacturer(request.getManufacturer());
//            cpuFromDB.setSocket(request.getSocket());
//            cpuFromDB.setTdp(request.getTdp());
//            cpuFromDB.setThreads(request.getThreads());
//            cpuFromDB.setBase_frequency(request.getBaseFrequency());
//            cpuFromDB.setTurbo_frequency(request.getTurboFrequency());
//            cpuFromDB.setLaunch_date(request.getLaunchDate().toGregorianCalendar().getTime());
//            // 3. update the movie in database
//
//            boolean flag = service.updateEntity(cpuFromDB);
//
//            if(flag == false) {
//                serviceStatus.setStatusCode("CONFLICT");
//                serviceStatus.setMessage("Exception while updating Entity=" + request.getName());;
//            }else {
//                serviceStatus.setStatusCode("SUCCESS");
//                serviceStatus.setMessage("Content updated Successfully");
//            }
//
//
//        }
//
//        response.setServiceStatus(serviceStatus);
//        return response;
//    }
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteMovieRequest")
//    @ResponsePayload
//    public DeleteCpuResponse deleteCpu(@RequestPayload DeleteCpuRequest request) {
//        DeleteCpuResponse response = new DeleteCpuResponse();
//        ServiceStatus serviceStatus = new ServiceStatus();
//
//        boolean flag = service.deleteEntity(request.getId());
//
//        if (flag == false) {
//            serviceStatus.setStatusCode("FAIL");
//            serviceStatus.setMessage("Exception while deletint Entity id=" + request.getId());
//        } else {
//            serviceStatus.setStatusCode("SUCCESS");
//            serviceStatus.setMessage("Content Deleted Successfully");
//        }
//
//        response.setServiceStatus(serviceStatus);
//        return response;
//    }


}
