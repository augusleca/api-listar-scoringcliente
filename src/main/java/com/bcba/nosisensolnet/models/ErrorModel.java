package com.bcba.nosisensolnet.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@AllArgsConstructor
@Schema(description = "Respuesta de error de la API")
public class ErrorModel {

    @Schema(description = "Codigo de error particular", example = "XXXXXXXX")
    private final String codigo;

    @Schema(description = "Descripcion de error", example = "Descripcion de error")
    private final String descripcion;
}
