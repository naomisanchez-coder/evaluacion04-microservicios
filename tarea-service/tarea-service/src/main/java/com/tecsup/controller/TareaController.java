package com.tecsup.controller;

import com.tecsup.dto.ProyectoDTO;
import com.tecsup.model.Tarea;
import com.tecsup.service.TareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private static final Logger logger = LoggerFactory.getLogger(TareaController.class);

    private final TareaService tareaService;

    @Value("${mensaje.servicio}")
    private String mensajeServicio;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping("/mensaje")
    public String mensaje() {
        return mensajeServicio;
    }

    @GetMapping
    public List<Tarea> listar() {
        logger.info("Listando tareas");
        return tareaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> buscarPorId(@PathVariable Long id) {
        logger.info("Buscando tarea con id: {}", id);

        Tarea tarea = tareaService.buscarPorId(id);

        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tarea);
    }

    @GetMapping("/{id}/proyecto")
    public ResponseEntity<ProyectoDTO> obtenerProyecto(@PathVariable Long id) {
        Tarea tarea = tareaService.buscarPorId(id);

        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }

        ProyectoDTO proyecto = tareaService.obtenerProyecto(tarea.getProyectoId());
        return ResponseEntity.ok(proyecto);
    }

    @PostMapping
    public Tarea guardar(@RequestBody Tarea tarea) {
        logger.info("Registrando tarea: {}", tarea.getTitulo());
        return tareaService.guardar(tarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(@PathVariable Long id, @RequestBody Tarea tarea) {
        logger.info("Actualizando tarea con id: {}", id);

        Tarea actualizada = tareaService.actualizar(id, tarea);

        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logger.info("Eliminando tarea con id: {}", id);

        boolean eliminada = tareaService.eliminar(id);

        if (!eliminada) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}