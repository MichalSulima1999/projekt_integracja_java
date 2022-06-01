package com.example.projekt.api;

import com.example.projekt.domain.CpuEntity;
import com.example.projekt.service.CpuEntityService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CpuResource {
    private Gson gson = new Gson();
    private final CpuEntityService cpuEntityService;

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

    @GetMapping("/cpu/download/json")
    public ResponseEntity<Resource> downloadJson() throws IOException {

        String file = gson.toJson(cpuEntityService.getAllEntities());
        log.info(file);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header("Content-Disposition")
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
