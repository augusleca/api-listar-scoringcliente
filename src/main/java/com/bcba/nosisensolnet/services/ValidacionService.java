package com.bcba.nosisensolnet.services;

import com.bcba.nosisensolnet.errors.exceptions.validations.LongitudException;
import com.bcba.nosisensolnet.errors.exceptions.validations.TipoDeDatoException;
import com.bcba.nosisensolnet.errors.exceptions.validations.ValorNegativoException;
import com.bcba.nosisensolnet.utils.Propiedades;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ValidacionService {

    private final Propiedades propiedades;

    @Autowired
    public ValidacionService(Propiedades propiedades) {
        this.propiedades = propiedades;
    }

    public void validar(String cuitConGuionesyPuntos) {

        log.info("Se recibio una solicitud para validar numero de CUIT");

        String cuit = this.simplificarCUIT(cuitConGuionesyPuntos);

        this.validarTipoDeDato(cuit);

        this.validarLongitud(cuit);

        log.info("Se proceso la solicitud de validacion de numero de CUIT");

    }

    private String simplificarCUIT(String cuit) {
        if (cuit.startsWith("-")) throw new ValorNegativoException("cuit");
        return cuit
            .replace("-", "")
            .replace(".", "");
    }

    private void validarTipoDeDato(String cuit) {
        try {
            Long.parseLong(cuit);
        } catch (NumberFormatException e) {
            throw new TipoDeDatoException("CUIT", "numerico");
        }
    }

    private void validarLongitud(String cuit) {
        if (cuit.length() != 11)
            throw new LongitudException("CUIT", "11");
    }
}