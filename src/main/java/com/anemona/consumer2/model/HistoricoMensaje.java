package com.anemona.consumer2.model;

import java.time.LocalDateTime;
import java.util.List;

import com.anemona.consumer2.DTO.AlertaDTO;
import com.anemona.consumer2.DTO.EstadoVitalDTO;

import lombok.Data;

@Data
public class HistoricoMensaje {
    private LocalDateTime fechainicio;
    private LocalDateTime fechaFin;
    private List<EstadoVitalDTO> estadosVitales;
    private List<AlertaDTO> alertas;
    private int totalEstadosVitales;
    private int totalAlertas;
}
