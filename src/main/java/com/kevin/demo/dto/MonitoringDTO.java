package com.kevin.demo.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

public class MonitoringDTO {
    
    @Data
    @Builder
    @Schema(description = "Resumen estadístico de las mediciones de temperatura de un lote.")
    public static class TemperatureSummary {
        @Schema(description = "Temperatura actual en grados Celsius", example = "42.5")
        private Double currentTemperature;
        
        @Schema(description = "Temperatura máxima registrada", example = "45.0")
        private Double maximumTemperature;
        
        @Schema(description = "Temperatura mínima registrada", example = "38.2")
        private Double minimumTemperature;
        
        @Schema(description = "Temperatura promedio", example = "43.1")
        private Double averageTemperature;
    }
    
    @Data
    @Builder
    @Schema(description = "Datos agregados para mostrar en la pantalla principal del panel de control.")
    public static class Dashboard {
        @Schema(description = "Cantidad de lotes agrupados por estado", example = "{\"COMPLETED\": 5, \"HEATING\": 2}")
        private Map<String, Long> batchCounts;
        
        @Schema(description = "Número total de lotes activos", example = "3")
        private Long activeBatchesCount;
        
        @Schema(description = "Cantidad de lotes terminados hoy", example = "12")
        private Integer completedToday;
    }
}
