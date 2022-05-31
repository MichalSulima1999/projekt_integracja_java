package com.example.projekt.api;

import com.example.projekt.domain.CpuEntity;
import com.example.projekt.service.CpuEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
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
    private final CpuEntityService cpuEntityService;

    @GetMapping("/cpu")
    public ResponseEntity<List<CpuEntity>> showCpus(HttpServletResponse response) {
        return ResponseEntity.ok().body(cpuEntityService.getAllEntities());
    }

    @GetMapping("/cpu/{id}")
    public ResponseEntity<CpuEntity> showCpu(@PathVariable long id, HttpServletResponse response) {
        return ResponseEntity.ok().body(cpuEntityService.getEntityById(id));
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
    public boolean deleteCpu(@PathVariable long id, HttpServletResponse response) throws IOException {
        if (cpuEntityService.deleteEntity(id)) {
            ResponseEntity.ok().body("Deleted successfully!");
            return true;
        } else {
            ResponseEntity.status(NOT_FOUND).body("Error!");
        }
        return false;
    }
}
