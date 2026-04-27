package com.kevin.demo.domain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kevin.demo.domain.model.Recipe;
import com.kevin.demo.domain.service.RecipeService;
import com.kevin.demo.dto.RecipeDTO;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@Tag(name = "Gestión de Recetas", description = "Operaciones para crear, modificar y buscar recetas de nuestra fábrica de yogurt")
public class RecipeController {
    
    private final RecipeService recipeService;
    
    @Operation(summary = "Crear una nueva receta", description = "Registra una nueva receta en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Receta creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de la receta inválidos")
    })
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = recipeService.createRecipe(recipeDTO);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Actualizar una receta", description = "Modifica los datos de una receta existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta actualizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = recipeService.updateRecipe(id, recipeDTO);
        return ResponseEntity.ok(recipe);
    }
    
    @Operation(summary = "Obtener una receta por ID", description = "Busca y devuelve los detalles de una receta específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta encontrada"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        return ResponseEntity.ok(recipe);
    }
    
    @Operation(summary = "Obtener todas las recetas activas", description = "Devuelve una lista con todas las recetas que están actualmente activas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllActiveRecipes());
    }
    
    @Operation(summary = "Buscar recetas", description = "Busca recetas activas que coincidan con la palabra clave")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada correctamente")
    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam String keyword) {
        return ResponseEntity.ok(recipeService.searchRecipes(keyword));
    }
    
    @Operation(summary = "Desactivar una receta", description = "Cambia el estado de la receta a inactiva")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta desactivada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateRecipe(@PathVariable Long id) {
        recipeService.deactivateRecipe(id);
        return ResponseEntity.ok().build();
    }
    
    @Operation(summary = "Activar una receta", description = "Cambia el estado de la receta a activa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta activada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateRecipe(@PathVariable Long id) {
        recipeService.activateRecipe(id);
        return ResponseEntity.ok().build();
    }
}