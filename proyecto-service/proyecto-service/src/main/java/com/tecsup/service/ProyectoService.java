package com.tecsup.service;

import com.tecsup.client.UsuarioClient;
import com.tecsup.dto.UsuarioDTO;
import com.tecsup.model.Proyecto;
import com.tecsup.repository.ProyectoRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final UsuarioClient usuarioClient;

    public ProyectoService(ProyectoRepository proyectoRepository, UsuarioClient usuarioClient) {
        this.proyectoRepository = proyectoRepository;
        this.usuarioClient = usuarioClient;
    }

    public List<Proyecto> listar() {
        return proyectoRepository.findAll();
    }

    public Proyecto buscarPorId(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public Proyecto actualizar(Long id, Proyecto proyecto) {
        Proyecto existente = buscarPorId(id);

        if (existente == null) {
            return null;
        }

        existente.setNombre(proyecto.getNombre());
        existente.setDescripcion(proyecto.getDescripcion());
        existente.setEstado(proyecto.getEstado());
        existente.setAvance(proyecto.getAvance());
        existente.setResponsableId(proyecto.getResponsableId());

        return proyectoRepository.save(existente);
    }

    public boolean eliminar(Long id) {
        if (!proyectoRepository.existsById(id)) {
            return false;
        }

        proyectoRepository.deleteById(id);
        return true;
    }

    @CircuitBreaker(name = "usuarioService", fallbackMethod = "fallbackUsuario")
    @Retry(name = "usuarioService")
    public UsuarioDTO obtenerResponsable(Long responsableId) {
        return usuarioClient.buscarUsuarioPorId(responsableId);
    }

    public UsuarioDTO fallbackUsuario(Long responsableId, Throwable throwable) {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(responsableId);
        usuario.setNombre("Servicio de usuarios no disponible");
        usuario.setCorreo("No fue posible consultar el servicio. Intente nuevamente.");
        usuario.setRol("NO DISPONIBLE");
        return usuario;
    }
}