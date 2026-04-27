package com.kevin.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto que representa un ingrediente necesario para preparar una receta de yogurt.")
public class IngredientDTO {
    @Schema(description = "Nombre del ingrediente", example = "Leche en polvo")
    private String name;
    
    @Schema(description = "Cantidad del ingrediente", example = "50.0")
    private Double quantity;
    
    @Schema(description = "Unidad de medida", example = "gramos")
    private String unit;
    
    @Schema(description = "Notas adicionales", example = "Disolver bien antes de agregar")
    private String notes;
    
    @Schema(description = "Indica si el ingrediente es opcional", example = "true")
    private Boolean optional;
}
