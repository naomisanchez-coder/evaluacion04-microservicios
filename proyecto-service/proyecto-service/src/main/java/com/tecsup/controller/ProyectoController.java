package com.tecsup.controller;

import com.tecsup.dto.UsuarioDTO;
import com.tecsup.model.Proyecto;
import com.tecsup.service.ProyectoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private static final Logger logger = LoggerFactory.getLogger(ProyectoController.class);

    private final ProyectoService proyectoService;

    @Value("${mensaje.servicio}")
    private String mensajeServicio;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping("/mensaje")
    public String mensaje() {
        return mensajeServicio;
    }

    @GetMapping
    public List<Proyecto> listar() {
        logger.info("Listando proyectos");
        return proyectoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> buscarPorId(@PathVariable Long id) {
        logger.info("Buscando proyecto con id: {}", id);

        Proyecto proyecto = proyectoService.buscarPorId(id);

        if (proyecto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(proyecto);
    }

    @GetMapping("/{id}/responsable")
    public ResponseEntity<UsuarioDTO> obtenerResponsable(@PathVariable Long id) {
        Proyecto proyecto = proyectoService.buscarPorId(id);

        if (proyecto == null) {
            return ResponseEntity.notFound().build();
        }

        UsuarioDTO responsable = proyectoService.obtenerResponsable(proyecto.getResponsableId());
        return ResponseEntity.ok(responsable);
    }

    @PostMapping
    public Proyecto guardar(@RequestBody Proyecto proyecto) {
        logger.info("Registrando proyecto: {}", proyecto.getNombre());
        return proyectoService.guardar(proyecto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizar(@PathVariable Long id, @RequestBody Proyecto proyecto) {
        logger.info("Actualizando proyecto con id: {}", id);

        Proyecto actualizado = proyectoService.actualizar(id, proyecto);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logger.info("Eliminando proyecto con id: {}", id);

        boolean eliminado = proyectoService.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}