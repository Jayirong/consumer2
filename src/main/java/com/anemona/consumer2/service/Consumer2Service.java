package com.anemona.consumer2.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anemona.consumer2.model.HistoricoMensaje;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer2Service {
    
    @Value("${historico.path:/app/historicos}")
    private String historicoPath;

    @RabbitListener(queues =  "historico.queue")
    public void procesarHistorico(HistoricoMensaje historico) {
        try {
            log.info("Recibiendo historico para procesar del periodo: {} a {}", historico.getFechainicio(), historico.getFechaFin());

            //crear directorio si no existe
            File dir = new File(historicoPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            //generar nombdre del archivo con el timestamp
            String fileName = String.format("historico_%s.json",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")));
            File file = new File(dir, fileName);

            //crear objectmapper con formato de fecha
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

            //escribir historico al archivo
            mapper.writeValue(file, historico);

            log.info("Historico guardado exitosamente en: {}", file.getAbsolutePath());

        } catch (Exception e) {
            log.error("Error procesando historico: {}", e.getMessage(), e);
        }
    }

}
