package com.kevin.demo.dto;

import java.util.List;
import com.kevin.demo.domain.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos que contiene toda la información necesaria para crear o editar una receta.")
public class RecipeDTO {
    @Schema(description = "Nombre de la receta", example = "Yogurt de Fresa")
    private String name;
    
    @Schema(description = "Descripción de la receta", example = "Yogurt dulce con trozos de fresa")
    private String description;
    
    @Schema(description = "Volumen de leche por defecto (litros)", example = "2.0")
    private Double defaultMilkVolume;
    
    @Schema(description = "Cantidad de fermento inicial por defecto", example = "4.0")
    private Double defaultStarterAmount;
    
    @Schema(description = "Temperatura de calentamiento (°C)", example = "85.0")
    private Double heatingTemperature;
    
    @Schema(description = "Duración del calentamiento (minutos)", example = "10")
    private Integer heatingDuration;
    
    @Schema(description = "Temperatura de inoculación (°C)", example = "45.0")
    private Double inoculationTemperature;
    
    @Schema(description = "Temperatura de incubación (°C)", example = "43.0")
    private Double incubationTemperature;
    
    @Schema(description = "Tiempo mínimo de incubación (horas)", example = "8")
    private Integer minIncubationTime;
    
    @Schema(description = "Tiempo máximo de incubación (horas)", example = "12")
    private Integer maxIncubationTime;
    
    @Schema(description = "Tiempo de refrigeración (horas)", example = "4")
    private Integer refrigerationTime;
    
    @Schema(description = "Nivel de dificultad", example = "BEGINNER")
    private Recipe.DifficultyLevel difficulty;
    
    @Schema(description = "Consejos para la preparación", example = "Usar leche entera")
    private String tips;
    
    @Schema(description = "Lista de ingredientes adicionales")
    private List<IngredientDTO> ingredients;
}
