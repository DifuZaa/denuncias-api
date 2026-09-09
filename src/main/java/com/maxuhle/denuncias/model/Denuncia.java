package com.maxuhle.denuncias.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Denuncia escolar. Basado en el caso del Colegio Max Uhle: registra fecha
 * y hora automáticamente, y marca si el tipo de denuncia obliga a activar
 * el protocolo de la Ley 29719.
 */
@Entity
@Data
public class Denuncia {

    // Tipos de denuncia que activan el protocolo de la Ley 29719
    private static final List<String> TIPOS_QUE_ACTIVAN_PROTOCOLO =
            List.of("MALTRATO_PSICOLOGICO", "MALTRATO_FISICO", "ACOSO_ESCOLAR");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoDenuncia;

    private String descripcion;

    private String nombreDenunciante;

    private String nombreAfectado;

    private LocalDateTime fechaRegistro;

    private String estado;

    private boolean protocoloActivado;

    @PrePersist
    public void alRegistrar() {
        this.fechaRegistro = LocalDateTime.now();
        this.protocoloActivado = tipoDenuncia != null
                && TIPOS_QUE_ACTIVAN_PROTOCOLO.contains(tipoDenuncia.toUpperCase());
        this.estado = protocoloActivado ? "PROTOCOLO_ACTIVADO" : "RECIBIDA";
    }
}
