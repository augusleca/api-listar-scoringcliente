package com.bcba.nosisensolnet.errors.exceptions.validations;

import com.bcba.nosisensolnet.errors.exceptions.IErrorException;

public class LongitudException extends IErrorException {

    public LongitudException() {
        super();
    }

    public LongitudException(String... valores) {
        super(valores);
    }

    @Override
    public String getCodigo() {
        return this.propiedades.getLongitudCodigo();
    }

    @Override
    public String getDescripcion() {
        return this.propiedades.reemplazarParametros(propiedades.getLongitudDescripcion(), valores);
    }

}