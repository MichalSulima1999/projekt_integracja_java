package com.example.projekt.api;

import com.example.projekt.domain.CpuEntity;
import com.example.projekt.service.CpuEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;

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

    @DeleteMapping("/cpu")
    public boolean deleteCpu(@RequestParam(name = "id") long id, HttpServletResponse response) throws IOException {
        if (cpuEntityService.deleteEntity(id)) {
            response.getWriter().write("Cpu deleted successfully!");
            return true;
        } else {
            response.getWriter().write("Error during delete!");
        }
        return false;
    }
}
