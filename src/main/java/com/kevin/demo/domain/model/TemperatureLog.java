package com.kevin.demo.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "temperature_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad de base de datos que registra una medición de temperatura en el tiempo.")
public class TemperatureLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del registro de temperatura", example = "1")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "batch_id", nullable = false)
    @Schema(description = "Lote al que pertenece esta medición")
    private YogurtBatch batch;
    
    @Column(nullable = false)
    @Schema(description = "Temperatura registrada en grados Celsius", example = "43.5")
    private Double temperature;
    
    @Column(nullable = false)
    @Schema(description = "Fecha y hora exacta de la medición", example = "2023-10-27T10:00:00")
    private LocalDateTime recordedAt;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Fase del proceso en la que se tomó la temperatura", example = "INCUBATION")
    private LogType type;
    
    @Schema(description = "Notas u observaciones de la medición", example = "Temperatura estable")
    private String notes;
    
    @Schema(description = "Tipos de fases en las que se puede registrar una medición térmica")
    public enum LogType {
        HEATING, COOLING, INCUBATION, REFRIGERATION, MANUAL
    }
}