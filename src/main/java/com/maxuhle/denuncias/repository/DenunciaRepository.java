package com.maxuhle.denuncias.repository;

import com.maxuhle.denuncias.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
}
