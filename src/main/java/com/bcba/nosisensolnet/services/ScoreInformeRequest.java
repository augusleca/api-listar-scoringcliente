package com.bcba.nosisensolnet.services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreInformeRequest {
    private String cuit;
    private String canal;
    private String modelo;
    private Integer campana;
}
