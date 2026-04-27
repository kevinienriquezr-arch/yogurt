package com.kevin.demo.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad principal que almacena las instrucciones y parámetros para preparar yogurt.")
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la receta", example = "1")
    private Long id;
    
    @Column(nullable = false, unique = true)
    @Schema(description = "Nombre de la receta", example = "Yogurt de Fresa")
    private String name;
    
    @Schema(description = "Descripción detallada de la receta", example = "Yogurt dulce con trozos de fresa fresca")
    private String description;
    
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @Builder.Default
    @Schema(description = "Lista de ingredientes vinculados a esta receta")
    private List<Ingredient> ingredients = new ArrayList<>();
    
    @Column(nullable = false)
    @Schema(description = "Volumen de leche por defecto en litros", example = "2.0")
    private Double defaultMilkVolume;
    
    @Column(nullable = false)
    @Schema(description = "Cantidad de fermento por defecto en cucharadas", example = "4.0")
    private Double defaultStarterAmount;
    
    @Column(nullable = false)
    @Schema(description = "Temperatura de calentamiento en °C", example = "85.0")
    private Double heatingTemperature;
    
    @Column(nullable = false)
    @Schema(description = "Duración del calentamiento en minutos", example = "10")
    private Integer heatingDuration;
    
    @Column(nullable = false)
    @Schema(description = "Temperatura ideal de inoculación en °C", example = "45.0")
    private Double inoculationTemperature;
    
    @Column(nullable = false)
    @Schema(description = "Temperatura de incubación en °C", example = "43.0")
    private Double incubationTemperature;
    
    @Column(nullable = false)
    @Schema(description = "Tiempo mínimo de incubación en horas", example = "8")
    private Integer minIncubationTime;
    
    @Column(nullable = false)
    @Schema(description = "Tiempo máximo de incubación en horas", example = "12")
    private Integer maxIncubationTime;
    
    @Column(nullable = false)
    @Schema(description = "Tiempo de refrigeración recomendado en horas", example = "4")
    private Integer refrigerationTime;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Dificultad de la preparación", example = "BEGINNER")
    private DifficultyLevel difficulty;
    
    @Schema(description = "Consejos adicionales para un mejor resultado", example = "Usar leche entera pasteurizada")
    private String tips;
    
    @Column(nullable = false)
    @Schema(description = "Indica si la receta está disponible para usarse", example = "true")
    private Boolean active;
    
    @Schema(description = "Niveles de dificultad para la elaboración de la receta en la fábrica")
    public enum DifficultyLevel {
        BEGINNER, INTERMEDIATE, ADVANCED
    }
}