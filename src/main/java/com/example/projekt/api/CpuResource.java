package com.example.projekt.api;

import com.example.projekt.domain.CpuEntity;
import com.example.projekt.file.json.JsonExporter;
import com.example.projekt.file.xml.Cpus;
import com.example.projekt.file.xml.XmlExporter;
import com.example.projekt.service.CpuEntityService;
import com.google.gson.Gson;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CpuResource {
    private Gson gson = new Gson();
    private final CpuEntityService cpuEntityService;
    @Autowired
    private JsonExporter jsonExporter;
    @Autowired
    private XmlExporter xmlExporter;

    @GetMapping("/cpu")
    public ResponseEntity<List<CpuEntity>> showCpus() {
        return ResponseEntity.ok().body(cpuEntityService.getAllEntities());
    }

    @GetMapping("/cpu/{id}")
    public ResponseEntity<CpuEntity> showCpu(@PathVariable long id) {
        return ResponseEntity.ok().body(cpuEntityService.getEntityById(id));
    }

    @GetMapping("/cpu/byDate")
    public ResponseEntity<List<CpuEntity>> showCpuByDate() {
        return ResponseEntity.ok().body(cpuEntityService.getAllEntitiesSortedByDate());
    }

    @GetMapping("/cpu/page/{offset}/{pageSize}")
    public ResponseEntity<Page<CpuEntity>> showCpusPaginated(@PathVariable int offset, @PathVariable int pageSize) {
        return ResponseEntity.ok().body(cpuEntityService.getAllEntitiesWithPagination(offset, pageSize));
    }

    @PostMapping("/cpu")
    public ResponseEntity<CpuEntity> addCpu(@RequestBody CpuEntity cpu, HttpServletResponse response) throws IOException {
        if(cpuEntityService.getEntityByName(cpu.getName()) != null) {
            response.getWriter().write("Cpu already exists");
            response.setStatus(FORBIDDEN.value());
            return null;
        }

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/cpu/add").toUriString());
        return ResponseEntity.created(uri).body(cpuEntityService.addEntity(cpu));
    }

    @PatchMapping("/cpu")
    public boolean editCpu(@RequestBody CpuEntity cpu, HttpServletResponse response) throws IOException {
        if(cpuEntityService.getEntityByName(cpu.getName()) != null) {
            response.getWriter().write("Cpu already exists");
            response.setStatus(FORBIDDEN.value());
            return false;
        }
        if (cpuEntityService.updateEntity(cpu)) {
            response.getWriter().write("Cpu updated!");
            return true;
        } else {
            response.getWriter().write("Error during update!");
        }
        return false;
    }

    @DeleteMapping("/cpu/{id}")
    public boolean deleteCpu(@PathVariable long id) throws IOException {
        if (cpuEntityService.deleteEntity(id)) {
            ResponseEntity.ok().body("Deleted successfully!");
            return true;
        } else {
            ResponseEntity.status(NOT_FOUND).body("Error!");
        }
        return false;
    }

    @DeleteMapping("/cpu/deleteMultiple")
    public boolean deleteCpus(@RequestBody IdWrapper ids) throws IOException {
        if (cpuEntityService.deleteEntities(ids.getIds())) {
            ResponseEntity.ok().body("Deleted successfully!");
            return true;
        } else {
            ResponseEntity.status(NOT_FOUND).body("Error!");
        }
        return false;
    }

    @GetMapping("/cpu/download/json")
    public ResponseEntity<byte[]> downloadJson() {
        List<CpuEntity> cpus = cpuEntityService.getAllEntities();
        String cpuJsonString = jsonExporter.export(cpus);
        byte[] cpuJsonBytes = cpuJsonString.getBytes(StandardCharsets.UTF_8);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=cpus.json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(cpuJsonBytes.length)
                .body(cpuJsonBytes);
    }

    @GetMapping("/cpu/download/xml")
    public ResponseEntity<byte[]> downloadXml() {
        List<CpuEntity> cpus = cpuEntityService.getAllEntities();
        String cpuXmlString = xmlExporter.jaxbObjectToXML(cpus);
        byte[] cpuJsonBytes = cpuXmlString.getBytes(StandardCharsets.UTF_8);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=cpus.xml")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(cpuJsonBytes.length)
                .body(cpuJsonBytes);
    }

    @PostMapping("/cpu/upload/json")
    public String uploadJson(@RequestParam("file") MultipartFile file) throws IOException {
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        List<CpuEntity> cpuEntities = jsonExporter.importJson(content);
        log.info(cpuEntities.toString());

        cpuEntityService.addEntities(cpuEntities);
        return "Success!";
    }

    @PostMapping("/cpu/upload/xml")
    public String uploadXml(@RequestParam("file") MultipartFile file) throws IOException, JAXBException {
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        Cpus cpus = xmlExporter.jaxbXmlToObject(content);
        log.info(cpus.toString());

        cpuEntityService.addEntities(cpus.getCpuEntities());
        return "Success!";
    }
}

@Data
class IdWrapper{
    List<Long> ids;
}