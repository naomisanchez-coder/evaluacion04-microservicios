package com.tecsup.service;

import com.tecsup.client.ProyectoClient;
import com.tecsup.dto.ProyectoDTO;
import com.tecsup.model.Tarea;
import com.tecsup.repository.TareaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;
    private final ProyectoClient proyectoClient;

    public TareaService(TareaRepository tareaRepository, ProyectoClient proyectoClient) {
        this.tareaRepository = tareaRepository;
        this.proyectoClient = proyectoClient;
    }

    public List<Tarea> listar() {
        return tareaRepository.findAll();
    }

    public Tarea buscarPorId(Long id) {
        return tareaRepository.findById(id).orElse(null);
    }

    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea actualizar(Long id, Tarea tarea) {
        Tarea existente = buscarPorId(id);

        if (existente == null) {
            return null;
        }

        existente.setTitulo(tarea.getTitulo());
        existente.setDescripcion(tarea.getDescripcion());
        existente.setEstado(tarea.getEstado());
        existente.setProyectoId(tarea.getProyectoId());

        return tareaRepository.save(existente);
    }

    public boolean eliminar(Long id) {
        if (!tareaRepository.existsById(id)) {
            return false;
        }

        tareaRepository.deleteById(id);
        return true;
    }

    @CircuitBreaker(name = "proyectoService", fallbackMethod = "fallbackProyecto")
    @Retry(name = "proyectoService")
    public ProyectoDTO obtenerProyecto(Long proyectoId) {
        return proyectoClient.buscarProyectoPorId(proyectoId);
    }

    public ProyectoDTO fallbackProyecto(Long proyectoId, Throwable throwable) {
        ProyectoDTO proyecto = new ProyectoDTO();
        proyecto.setId(proyectoId);
        proyecto.setNombre("Servicio de proyectos no disponible");
        proyecto.setDescripcion("No fue posible consultar el servicio. Intente nuevamente.");
        proyecto.setEstado("NO DISPONIBLE");
        proyecto.setAvance(0);
        proyecto.setResponsableId(null);
        return proyecto;
    }
}
