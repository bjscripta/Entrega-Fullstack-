package com.perfulandia.perfulandia.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.perfulandia.model.Atencion;
import com.perfulandia.perfulandia.repository.AtencionRepositoryJPA;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AtencionService {
    
    @Autowired
    public AtencionRepositoryJPA atencionRepository;

    public List<Atencion> getAtenciones(){
        return atencionRepository.findAll();
    }

    public Atencion getAtencionById(int idAtencion){
        return atencionRepository.findById(idAtencion)
                .orElseThrow(() -> new RuntimeException("Atencion no encontrada"));
    }

    public Atencion saveAtencion(Atencion atencion){
        return atencionRepository.save(atencion);
    }

    public String deleteAtencion(int idAtencion){
        atencionRepository.deleteById(idAtencion);
        return "Atencion elimnada!";
    }

    public Atencion actualizarEstadoAtencion(int idAtencion) {
        Atencion atencion = atencionRepository.findById(idAtencion)
                .orElseThrow(() -> new RuntimeException("Atención no encontrada: " + idAtencion));
        atencion.setEstadoAtencion("RESUELTO");
        return atencionRepository.save(atencion);
    }

}
