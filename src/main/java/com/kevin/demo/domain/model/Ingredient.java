package com.kevin.demo.domain.model;

import jakarta.persistence.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad de base de datos que representa un ingrediente asignado a una receta.")
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del ingrediente en la base de datos", example = "1")
    private Long id;
    
    @Column(nullable = false)
    @Schema(description = "Nombre del ingrediente", example = "Leche entera")
    private String name;
    
    @Schema(description = "Cantidad requerida del ingrediente", example = "1.5")
    private Double quantity;
    
    @Schema(description = "Unidad de medida (kg, g, ml, cucharadas, etc.)", example = "litros")
    private String unit;
    
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    @Schema(description = "Receta a la que pertenece este ingrediente")
    private Recipe recipe;
    
    @Schema(description = "Notas adicionales de preparación", example = "Hervir antes de usar")
    private String notes;
    
    @Column(nullable = false)
    @Schema(description = "Indica si el ingrediente es opcional", example = "false")
    private Boolean optional;
}