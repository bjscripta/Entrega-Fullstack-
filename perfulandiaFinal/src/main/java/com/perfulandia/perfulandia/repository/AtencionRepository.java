package com.perfulandia.perfulandia.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.perfulandia.perfulandia.model.Atencion;

@Repository
public class AtencionRepository {
    private List<Atencion> atenciones = new ArrayList<>();

    //Listar todos las atenciones
    public List<Atencion> mostrarAtenciones(){
        return atenciones;
    }

    //Buscar Atencion por id
    public Atencion buscarAtencionPorId(int idAtencion){
        for(Atencion a : atenciones) {
            if(a.getIdAtencion()==idAtencion){
                return a;
            }
        }
        return null;
    }

    //Agregar Atencion
    public Atencion agregarAtencion(Atencion a){
        atenciones.add(a);
        return a;
    }

    //Eliminar Atencion
    public void eliminarAtencion(int idAtencion){
        Atencion atencion = buscarAtencionPorId(idAtencion);
        if(atencion != null){
            atenciones.remove(atencion);
        }
    }

    //Actualizar el estado de la atencion
    public Atencion actualizarAtencionEstado(Atencion a){
        for (int  i = 0; i < atenciones.size(); i++){
            if (atenciones.get(i).getIdAtencion()==a.getIdAtencion()){
                Atencion aux = atenciones.get(i);
                aux.setEstadoAtencion("RESUELTO");
                return aux;
            }
        }
        return null;
    }
}
