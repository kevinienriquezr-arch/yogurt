package com.kevin.demo.domain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kevin.demo.domain.model.YogurtBatch;
import com.kevin.demo.domain.service.YogurtMakingService;
import com.kevin.demo.dto.BatchDTO;
import com.kevin.demo.dto.TemperatureRecordDTO;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
@Tag(name = "Control de Lotes", description = "Herramientas para gestionar todo el ciclo de vida de un lote en la fábrica")
public class YogurtBatchController {
    
    private final YogurtMakingService yogurtMakingService;
    
    @Operation(summary = "Crear nuevo lote", description = "Inicia un nuevo lote de yogurt indicando la receta, leche y fermento inicial")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Lote creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en los datos enviados")
    })
    @PostMapping
    public ResponseEntity<YogurtBatch> startNewBatch(@RequestBody BatchDTO.StartBatchRequest request) {
        YogurtBatch batch = yogurtMakingService.startNewBatch(
            request.getRecipeId(), 
            request.getCustomMilkVolume(), 
            request.getCustomStarterAmount()
        );
        return new ResponseEntity<>(batch, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Iniciar calentamiento", description = "Cambia el estado del lote a calentamiento usando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Calentamiento iniciado"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado")
    })
    @PostMapping("/{batchId}/heating")
    public ResponseEntity<YogurtBatch> startHeating(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startHeating(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Iniciar inoculación", description = "Avanza el lote a la fase de inoculación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inoculación iniciada"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado")
    })
    @PostMapping("/{batchId}/inoculating")
    public ResponseEntity<YogurtBatch> startInoculating(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startInoculating(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Iniciar incubación", description = "Avanza el lote a la fase de incubación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Incubación iniciada"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado")
    })
    @PostMapping("/{batchId}/incubation")
    public ResponseEntity<YogurtBatch> startIncubation(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startIncubation(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Iniciar refrigeración", description = "Avanza el lote a la fase de refrigeración final")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Refrigeración iniciada"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado")
    })
    @PostMapping("/{batchId}/refrigeration")
    public ResponseEntity<YogurtBatch> startRefrigeration(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startRefrigeration(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Completar lote", description = "Marca el proceso del lote como terminado con éxito")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lote completado"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado")
    })
    @PostMapping("/{batchId}/complete")
    public ResponseEntity<YogurtBatch> completeBatch(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.completeBatch(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Marcar lote como fallido", description = "Registra que el lote tuvo un problema y no puede continuar")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lote marcado como fallido"),
        @ApiResponse(responseCode = "400", description = "Motivo inválido o vacío"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado")
    })
    @PostMapping("/{batchId}/fail")
    public ResponseEntity<YogurtBatch> markAsFailed(
            @PathVariable Long batchId, 
            @RequestBody BatchDTO.FailRequest request) {
        YogurtBatch batch = yogurtMakingService.markAsFailed(batchId, request.getReason());
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Obtener todos los lotes", description = "Devuelve una lista con todos los lotes. Permite filtrar por estado")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<YogurtBatch>> getAllBatches(
            @RequestParam(required = false) YogurtBatch.BatchStatus status) {
        if (status != null) {
            return ResponseEntity.ok(yogurtMakingService.getBatchesByStatus(status));
        }
        return ResponseEntity.ok(yogurtMakingService.getAllBatches());
    }
    
    @Operation(summary = "Obtener un lote por ID", description = "Busca la información detallada de un lote específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lote encontrado"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado")
    })
    @GetMapping("/{batchId}")
    public ResponseEntity<YogurtBatch> getBatch(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.getBatch(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Registrar temperatura", description = "Guarda un registro de la temperatura actual del lote")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Temperatura registrada"),
        @ApiResponse(responseCode = "400", description = "Datos de temperatura inválidos"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado")
    })
    @PostMapping("/{batchId}/temperature")
    public ResponseEntity<Void> recordTemperature(
            @PathVariable Long batchId, 
            @RequestBody TemperatureRecordDTO request) {
        yogurtMakingService.recordTemperature(batchId, request.getTemperature(), request.getType());
        return ResponseEntity.ok().build();
    }
}
