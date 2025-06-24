package com.perfulandia.perfulandia.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.perfulandia.model.Envio;
import com.perfulandia.perfulandia.model.Proveedor;
import com.perfulandia.perfulandia.model.RutaEntrega;
import com.perfulandia.perfulandia.services.LogisticaService;

@WebMvcTest(LogisticaController.class)
public class LogisticaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LogisticaService logisticaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Envio envio;
    private Proveedor proveedor;
    private RutaEntrega rutaEntrega;

    @BeforeEach
    void setUp(){
        proveedor = new Proveedor();
        proveedor.setIdProveedor(1);
        proveedor.setNombre("Proveedor Uno");
        proveedor.setContacto("contacto@proveedor.com");

        rutaEntrega = new RutaEntrega();
        rutaEntrega.setIdRuta(1);
        rutaEntrega.setOrigen("Santiago");
        rutaEntrega.setDestino("Valparaiso");
        rutaEntrega.setDuracionEstimada(120);

        envio = new Envio();
        envio.setIdEnvio(1);
        envio.setProveedor(proveedor);
        envio.setRutaEntrega(rutaEntrega);
        envio.setEstado("EN TRANSITO");
        envio.setFechaEntregaEstimada("2025-06-25");     
    }

    @Test
    public void testListarEnvios() throws Exception {
        when(logisticaService.obtenerTodosLosEnvios()).thenReturn(List.of(envio));

        mockMvc.perform(get("/logistica/envios"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idEnvio").value(1))
            .andExpect(jsonPath("$[0].estado").value("EN TRANSITO"))
            .andExpect(jsonPath("$[0].proveedor.nombre").value("Proveedor Uno"))
            .andExpect(jsonPath("$[0].rutaEntrega.origen").value("Santiago"));
    }

    @Test
    public void testCrearEnvio() throws Exception {
        when(logisticaService.guardarEnvio(any(Envio.class))).thenReturn(envio);

        mockMvc.perform(post("/logistica/envios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(envio)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idEnvio").value(1))
            .andExpect(jsonPath("$.estado").value("EN TRANSITO"));
    }

    @Test
    public void testEliminarEnvio() throws Exception {
        doNothing().when(logisticaService).eliminarEnvio(1);

        mockMvc.perform(delete("/logistica/envios/1"))
            .andExpect(status().isOk());
    }

    @Test
    public void testListarProveedores() throws Exception {
        when(logisticaService.obtenerProveedores()).thenReturn(List.of(proveedor));

        mockMvc.perform(get("/logistica/proveedores"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idProveedor").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Proveedor Uno"));
    }

    @Test
    public void testCrearProveedor() throws Exception {
        when(logisticaService.guardarProveedor(any(Proveedor.class))).thenReturn(proveedor);

        mockMvc.perform(post("/logistica/proveedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proveedor)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idProveedor").value(1))
            .andExpect(jsonPath("$.nombre").value("Proveedor Uno"));
    }

    @Test
    public void testListarRutas() throws Exception {
        when(logisticaService.obtenerRutas()).thenReturn(List.of(rutaEntrega));

        mockMvc.perform(get("/logistica/rutas"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].origen").value("Santiago"))
            .andExpect(jsonPath("$[0].destino").value("Valparaiso"));
    }

    @Test
    public void testCrearRuta() throws Exception {
        when(logisticaService.guardarRuta(any(RutaEntrega.class))).thenReturn(rutaEntrega);

        mockMvc.perform(post("/logistica/rutas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rutaEntrega)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idRuta").value(1))
            .andExpect(jsonPath("$.origen").value("Santiago"))
            .andExpect(jsonPath("$.destino").value("Valparaiso"));
    }
}
