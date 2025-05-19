package com.perfulandia.perfulandia.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.perfulandia.perfulandia.model.Reporte;

@Repository
public class ReporteRepository {
    private List<Reporte> reportes = new ArrayList<>();

    //Mostrar todos los reportes
    public List<Reporte> mostrarReportes(){
        return reportes;
    }

    //Buscar reporte por id;
    public Reporte buscarReportePorId(int idReporte){
        for (Reporte r : reportes) {
            if(r.getIdReporte() == idReporte){
                return r;
            }
        }
        return null;
    }

    //Agregar reporte
    public Reporte agregaReporte(Reporte r){
        reportes.add(r);
        return r;
    }

    //Eliminar reporte
    public void eliminarReporte(int idReporte){
        Reporte reporte = buscarReportePorId(idReporte);
        if(reporte != null){
            reportes.remove(reporte);
        }
    }

    //Actulizar estado de la atencion
    public Reporte actualizarReporteEstado(Reporte r){
        for (int i = 0; i < reportes.size(); i++) {
            if (reportes.get(i).getIdReporte() == r.getIdReporte()) {
                Reporte aux = reportes.get(i);
                aux.setEstadoReporte("RESUELTO");
                return aux;
            }
        }
        return null;
    }
    
}
