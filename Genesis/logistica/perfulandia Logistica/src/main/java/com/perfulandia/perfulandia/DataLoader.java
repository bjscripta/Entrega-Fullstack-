package com.perfulandia.perfulandia;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Component;

import com.perfulandia.perfulandia.model.Envio;
import com.perfulandia.perfulandia.model.Proveedor;
import com.perfulandia.perfulandia.model.RutaEntrega;
import com.perfulandia.perfulandia.model.Usuario;
import com.perfulandia.perfulandia.repository.EnvioRepository;
import com.perfulandia.perfulandia.repository.ProveedorRepository;
import com.perfulandia.perfulandia.repository.RutaEntregaRepository;
import com.perfulandia.perfulandia.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;

@Component
public class DataLoader {

    private final ProveedorRepository proveedorRepository;
    private final RutaEntregaRepository rutaEntregaRepository;
    private final EnvioRepository envioRepository;
    private final UsuarioRepository usuarioRepository;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    public DataLoader(
            ProveedorRepository proveedorRepository,
            RutaEntregaRepository rutaEntregaRepository,
            EnvioRepository envioRepository,
            UsuarioRepository usuarioRepository){
        this.proveedorRepository = proveedorRepository;
        this.rutaEntregaRepository = rutaEntregaRepository;
        this.envioRepository = envioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostConstruct
    public void loadData(){
        if (proveedorRepository.count()==0 && rutaEntregaRepository.count()==0 && envioRepository.count()==0 && usuarioRepository.count()==0){

            //Crear usuarios
            List<Usuario> usuarios = new ArrayList<>();
            Usuario.EstadoUsuario[] estados = Usuario.EstadoUsuario.values();
            for(int i = 0; i < 10; i++){
                Usuario u = new Usuario();
                u.setNombre(faker.name().fullName());
                u.setCorreo(faker.internet().emailAddress());
                u.setContrasena(faker.internet().password(8,12));
                u.setEstado(estados[random.nextInt(estados.length)]);
                u.setPermisos(List.of("PERMISO_"+ faker.lorem().word().toUpperCase()));
                usuarios.add(usuarioRepository.save(u));
            }

            //Crear proveedores
            List<Proveedor> proveedores = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                Proveedor p = new Proveedor();
                p.setNombre(faker.company().name());
                p.setContacto(faker.phoneNumber().cellPhone());
                proveedores.add(proveedorRepository.save(p));
            }

            //Crear rutas de entrega
            List<RutaEntrega> rutas = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                RutaEntrega r = new RutaEntrega();
                r.setOrigen(faker.address().city());
                r.setDestino(faker.address().cityName());
                r.setDuracionEstimada(faker.number().numberBetween(30, 600));
                rutas.add(rutaEntregaRepository.save(r));
            }

            //Crear envios
            for(int i = 0; i < 10; i++){
                Envio e = new Envio();
                e.setProveedor(proveedores.get(random.nextInt(proveedores.size())));
                e.setRutaEntrega(rutas.get(random.nextInt(rutas.size())));
                e.setEstado(faker.options().option("Pendiente", "En tránsito", "Entregado"));
                e.setFechaEntregaEstimada(LocalDate.now().plusDays(faker.number().numberBetween(1, 10)).toString()); //fecha como string
                envioRepository.save(e);
            }
        }
    }
}
