package com.kevin.demo.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "yogurt_batches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa un lote de producción específico, registrando su progreso y tiempos.")
public class YogurtBatch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del lote", example = "1")
    private Long id;
    
    @Column(nullable = false)
    @Schema(description = "Código autogenerado para identificar el lote", example = "YB-1698425120000")
    private String batchCode;
    
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    @Schema(description = "Receta utilizada para producir este lote")
    private Recipe recipe;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Estado actual del proceso de producción", example = "INCUBATING")
    private BatchStatus status;
    
    @Column(nullable = false)
    @Schema(description = "Volumen de leche utilizado en litros", example = "3.5")
    private Double milkVolume;
    
    @Column(nullable = false)
    @Schema(description = "Cantidad de fermento utilizado en cucharadas", example = "5.0")
    private Double starterAmount;
    
    @Column(nullable = false)
    @Schema(description = "Temperatura objetivo actual dependiendo del estado", example = "43.0")
    private Double targetTemperature;
    
    @Column(nullable = false)
    @Schema(description = "Tiempo de incubación configurado en horas", example = "10")
    private Integer incubationTime;
    
    @Schema(description = "Fecha y hora de inicio de todo el proceso")
    private LocalDateTime startTime;
    
    @Schema(description = "Fecha y hora en la que comenzó la incubación")
    private LocalDateTime incubationStartTime;
    
    @Schema(description = "Fecha y hora esperada de finalización de incubación")
    private LocalDateTime incubationEndTime;
    
    @Schema(description = "Fecha y hora en la que comenzó la refrigeración")
    private LocalDateTime refrigerationStartTime;
    
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    @Builder.Default
    @Schema(description = "Registro histórico de temperaturas tomadas a este lote")
    private List<TemperatureLog> temperatureLogs = new ArrayList<>();
    
    @Schema(description = "Notas sobre incidencias durante la producción", example = "Todo marcha bien")
    private String notes;
    
    @Column(nullable = false)
    @Schema(description = "Fecha de creación del registro en base de datos")
    private LocalDateTime createdAt;
    
    @Schema(description = "Fecha de última actualización del registro")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        batchCode = "YB-" + System.currentTimeMillis();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    @Schema(description = "Fases por las que pasa un lote durante su producción en la fábrica")
    public enum BatchStatus {
        PREPARING, 
        HEATING, 
        COOLING, 
        INOCULATING, 
        INCUBATING, 
        REFRIGERATING, 
        COMPLETED, 
        FAILED
    }
}
