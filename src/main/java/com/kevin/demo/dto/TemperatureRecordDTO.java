package com.kevin.demo.dto;

import com.kevin.demo.domain.model.TemperatureLog;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Estructura de datos para enviar una nueva medición de temperatura de un lote en proceso.")
public class TemperatureRecordDTO {
    @Schema(description = "Valor de la temperatura medida", example = "43.5")
    private Double temperature;
    
    @Schema(description = "Fase en la que se realizó la medición", example = "INCUBATION")
    private TemperatureLog.LogType type;
}
