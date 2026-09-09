package com.maxuhle.denuncias.controller;

import com.maxuhle.denuncias.model.Denuncia;
import com.maxuhle.denuncias.service.DenunciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/denuncias")
public class DenunciaController {

    private final DenunciaService denunciaService;

    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @PostMapping
    public ResponseEntity<Denuncia> crear(@RequestBody Denuncia denuncia) {
        Denuncia guardada = denunciaService.crear(denuncia);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    @GetMapping
    public List<Denuncia> listar() {
        return denunciaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> obtenerPorId(@PathVariable Long id) {
        return denunciaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> actualizar(@PathVariable Long id, @RequestBody Denuncia datos) {
        return denunciaService.actualizar(id, datos)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!denunciaService.eliminar(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}