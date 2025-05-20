package com.perfulandia.perfulandia.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.perfulandia.model.Reporte;
import com.perfulandia.perfulandia.repository.ReporteRepositoryJPA;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class ReporteService {

    @Autowired
    public ReporteRepositoryJPA reporteRepository;

    public List<Reporte> getReportes(){
        return reporteRepository.findAll();
    }

    public Reporte getReporteById(int idReporte) {
        return reporteRepository.findById(idReporte)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
    }
    

    public Reporte saveReporte(Reporte reporte){
        return reporteRepository.save(reporte);
    }

    public String deleteReporte(int idReporte){
        reporteRepository.deleteById(idReporte);
        return "Reporte eliminado!";
    }

    public Reporte actualizarEstadoReporte(int idReporte) {
        Reporte reporte = reporteRepository.findById(idReporte)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado:" + idReporte));
        reporte.setEstadoReporte("RESUELTO");
        return reporteRepository.save(reporte);
    }

}
