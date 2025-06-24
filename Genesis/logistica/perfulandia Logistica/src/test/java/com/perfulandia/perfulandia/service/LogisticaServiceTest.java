package com.perfulandia.perfulandia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.perfulandia.perfulandia.model.Envio;
import com.perfulandia.perfulandia.model.Proveedor;
import com.perfulandia.perfulandia.model.RutaEntrega;
import com.perfulandia.perfulandia.repository.EnvioRepository;
import com.perfulandia.perfulandia.repository.ProveedorRepository;
import com.perfulandia.perfulandia.repository.RutaEntregaRepository;
import com.perfulandia.perfulandia.services.LogisticaService;

@ExtendWith(MockitoExtension.class)
public class LogisticaServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @Mock
    private ProveedorRepository proveedorRepository;

    @Mock
    private RutaEntregaRepository rutaEntregaRepository;

    @InjectMocks
    private LogisticaService logisticaService;

    private Envio envio;
    private Proveedor proveedor;
    private RutaEntrega rutaEntrega;

    @BeforeEach
    void setUp(){
        proveedor = new Proveedor();
        proveedor.setIdProveedor(1);
        proveedor.setNombre("Proveedor 1");
        proveedor.setContacto("proveedor@correo.cl");

        rutaEntrega = new RutaEntrega();
        rutaEntrega.setIdRuta(1);
        rutaEntrega.setOrigen("Santiago");
        rutaEntrega.setDestino("Valparaiso");
        rutaEntrega.setDuracionEstimada(90);

        envio = new Envio();
        envio.setIdEnvio(1);
        envio.setEstado("EN TRANSITO");
        envio.setFechaEntregaEstimada("2025-06-25");
        envio.setProveedor(proveedor);
        envio.setRutaEntrega(rutaEntrega);
    }

    @Test
    void testGuardarEnvio_Succes(){
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        when(rutaEntregaRepository.findById(1)).thenReturn(Optional.of(rutaEntrega));
        when(envioRepository.save(any(Envio.class))).thenReturn(envio);

        Envio result = logisticaService.guardarEnvio(envio);

        assertNotNull(result);
        assertEquals("EN TRANSITO", result.getEstado());
        verify(proveedorRepository, times(1)).findById(1);
        verify(rutaEntregaRepository, times(1)).findById(1);
        verify(envioRepository, times(1)).save(envio);
    }

    @Test
    void testGuardarEnvio_ProveedorNoExiste(){
        when(proveedorRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            logisticaService.guardarEnvio(envio);
        });

        assertEquals("Proveedor no encontrado", ex.getMessage());
    }

    @Test
    void testGuardarEnvio_RutaNoExiste() {
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        when(rutaEntregaRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            logisticaService.guardarEnvio(envio);
        });

        assertEquals("Ruta no encontrada", ex.getMessage());
    }

    @Test
    void testEliminarEnvio() {
        doNothing().when(envioRepository).deleteById(1);
        logisticaService.eliminarEnvio(1);
        verify(envioRepository).deleteById(1);
    }

    @Test
    void testObtenerTodosLosEnvios() {
        when(envioRepository.findAll()).thenReturn(List.of(envio));
        List<Envio> envios = logisticaService.obtenerTodosLosEnvios();
        assertEquals(1, envios.size());
    }

    @Test
    void testGuardarProveedor() {
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);
        Proveedor saved = logisticaService.guardarProveedor(proveedor);
        assertEquals("Proveedor 1", saved.getNombre());
    }

    @Test
    void testGuardarRuta() {
        when(rutaEntregaRepository.save(rutaEntrega)).thenReturn(rutaEntrega);
        RutaEntrega saved = logisticaService.guardarRuta(rutaEntrega);
        assertEquals("Santiago", saved.getOrigen());
    }
}
