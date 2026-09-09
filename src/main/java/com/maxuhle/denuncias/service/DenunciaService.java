package com.maxuhle.denuncias.service;

import com.maxuhle.denuncias.model.Denuncia;
import com.maxuhle.denuncias.repository.DenunciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DenunciaService {

    private final DenunciaRepository denunciaRepository;

    public DenunciaService(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    public List<Denuncia> listarTodas() {
        return denunciaRepository.findAll();
    }

    public Optional<Denuncia> obtenerPorId(Long id) {
        return denunciaRepository.findById(id);
    }

    public Denuncia crear(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    public Optional<Denuncia> actualizar(Long id, Denuncia datos) {
        return denunciaRepository.findById(id)
                .map(existente -> {
                    existente.setEstado(datos.getEstado());
                    existente.setDescripcion(datos.getDescripcion());
                    return denunciaRepository.save(existente);
                });
    }

    public boolean eliminar(Long id) {
        if (!denunciaRepository.existsById(id)) {
            return false;
        }
        denunciaRepository.deleteById(id);
        return true;
    }
}