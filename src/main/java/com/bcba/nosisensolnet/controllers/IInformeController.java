package com.bcba.nosisensolnet.controllers;

import com.bcba.nosisensolnet.models.ErrorModel;
import com.bcba.nosisensolnet.services.ScoreInformeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Tag(name = "Informe")
@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Bad Request (Fallo alguna validacion de los campos)", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))}),
    @ApiResponse(responseCode = "429", description = "Too Many Requests (Demasiadas requests en poco tiempo)", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))}),
    @ApiResponse(responseCode = "503", description = "Service Unavailable (Error no esperado)", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))}),
})
public interface IInformeController {
    @Operation(
        summary = "Listar variables de scoring",
        description = "Obtener mediante un CUIT, un canal, un modelo y una campaña el scoring de un cliente",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ScoreInformeRequest.class),
                examples = @ExampleObject(
                    name = "Ejemplo de solicitud",
                    summary = "Ejemplo 1",
                    value = "{\"cuit\":\"20297187717\",\"canal\":\"OB\",\"modelo\":\"1\",\"campana\":0}"
                )
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se obtienen las variables de scoring del cliente",
            content = {@Content(mediaType = "application/json", examples = @ExampleObject(value = "{\n" +
                "  \"Cuota\": 128601,\n" +
                "  \"CuotaUVA\": 0,\n" +
                "  \"Limite\": 500000,\n" +
                "  \"mensaje\": \"\",\n" +
                "  \"ingMinimo\": 633000,\n" +
                "  \"ingMaximo\": 633000,\n" +
                "  \"CUIT\": \"20297187717\"\n" +
                "}"))}),
    })

    public ResponseEntity<Map<String, Object>> getScoreInforme(
        @RequestParam String cuit,
        @RequestParam String canal,
        @RequestParam String modelo,
        @RequestParam Integer campana
    );
}

//public interface IInformeController {
//    @Operation(description = "Dado un CUIT, canal, modelo y campaña se obtiene el scoring de un clente")
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "Se obtienen las variables de scoring del cliente", content = {@Content(mediaType = "application/json")}),
//    })
//
//    public ResponseEntity<Map<String, Object>> getScoreInforme(@RequestBody ScoreInformeRequest request);
//}