package com.kevin.demo.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

public class BatchDTO {
    
    @Data
    @Schema(description = "Petición para iniciar la producción de un nuevo lote de yogurt.")
    public static class StartBatchRequest {
        @Schema(description = "Identificador de la receta a utilizar", example = "1")
        private Long recipeId;
        
        @Schema(description = "Volumen de leche en litros (opcional, sobreescribe la receta)", example = "3.5")
        private Double customMilkVolume;
        
        @Schema(description = "Cantidad de fermento (opcional, sobreescribe la receta)", example = "1.5")
        private Double customStarterAmount;
    }
    
    @Data
    @Schema(description = "Petición para marcar un lote como fallido proporcionando una justificación.")
    public static class FailRequest {
        @Schema(description = "Motivo por el cual el lote falló", example = "Corte de energía durante la incubación")
        private String reason;
    }
}
