package com.tecsup.client;

import com.tecsup.dto.ProyectoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "proyecto-service")
public interface ProyectoClient {

    @GetMapping("/api/proyectos/{id}")
    ProyectoDTO buscarProyectoPorId(@PathVariable Long id);
}