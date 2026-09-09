package com.maxuhle.denuncias.controller;

import com.maxuhle.denuncias.model.Denuncia;
import com.maxuhle.denuncias.repository.DenunciaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/denuncias")
public class DenunciaController {

    private final DenunciaRepository denunciaRepository;

    public DenunciaController(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    // POST /api/denuncias
    @PostMapping
    public ResponseEntity<Denuncia> crear(@RequestBody Denuncia denuncia) {
        Denuncia guardada = denunciaRepository.save(denuncia);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    // GET /api/denuncias
    @GetMapping
    public List<Denuncia> listar() {
        return denunciaRepository.findAll();
    }

    // GET /api/denuncias/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> obtenerPorId(@PathVariable Long id) {
        return denunciaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/denuncias/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> actualizar(@PathVariable Long id, @RequestBody Denuncia datos) {
        return denunciaRepository.findById(id)
                .map(existente -> {
                    existente.setEstado(datos.getEstado());
                    existente.setDescripcion(datos.getDescripcion());
                    Denuncia actualizada = denunciaRepository.save(existente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/denuncias/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!denunciaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        denunciaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
