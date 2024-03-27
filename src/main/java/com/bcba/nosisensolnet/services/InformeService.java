package com.bcba.nosisensolnet.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@Service
public class InformeService {

    private final ValidacionService validacionService;

    private final JdbcTemplate jdbcTemplate; // no estoy seguro si es final

    @Autowired
    public InformeService(ValidacionService validacionService, JdbcTemplate jdbcTemplate) {
        this.validacionService = validacionService;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> getScoreInforme(ScoreInformeRequest request) {

        log.info("Se recibio una solicitud para insertar un informe (cuit: {})", request.getCuit());
        this.validacionService.validar(request.getCuit());

        // DEBERIAMOS VALIDAR LOS OTROS CAMPOS?

        // Define el nombre del stored procedure y el RowMapper
        String storedProcedureName = "NET_CalculoNosis";

        // Ejecuta el stored procedure y obtén el resultado
        return jdbcTemplate.queryForMap(
            "EXEC " + storedProcedureName + " ?, ?, ?, ?",
            request.getCuit(),
            request.getCanal(),
            request.getModelo(),
            request.getCampana()
        );
    }
}

