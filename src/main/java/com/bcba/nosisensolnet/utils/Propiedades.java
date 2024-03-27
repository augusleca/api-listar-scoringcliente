package com.bcba.nosisensolnet.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Propiedades {

    @Value("${properties.exceptions.longitud.codigo}")
    private String longitudCodigo;
    @Value("${properties.exceptions.longitud.descripcion}")
    private String longitudDescripcion;
    @Value("${properties.exceptions.valorNegativo.codigo}")
    private String valorNegativoCodigo;
    @Value("${properties.exceptions.valorNegativo.descripcion}")
    private String valorNegativoDescripcion;
    @Value("${properties.exceptions.tipoDeDato.codigo}")
    private String tipoDeDatoCodigo;
    @Value("${properties.exceptions.tipoDeDato.descripcion}")
    private String tipoDeDatoDescripcion;
    @Value("${properties.exceptions.rateLimiter.codigo}")
    private String rateLimiterCodigo;
    @Value("${properties.exceptions.rateLimiter.descripcion}")
    private String rateLimiterDescripcion;
    @Value("${properties.exceptions.circuitBreaker.codigo}")
    private String circuitBreakerCodigo;
    @Value("${properties.exceptions.circuitBreaker.descripcion}")
    private String circuitBreakerDescripcion;

    public String reemplazarParametros(String descripcion, String... valores) {

        if (valores == null || valores.length == 0) {
            return descripcion;
        }

        for (int i = 1 ; i <= valores.length ; i++) {
            descripcion = descripcion.replace("%"+i, valores[i-1]);
        }
        return descripcion;

    }

}
