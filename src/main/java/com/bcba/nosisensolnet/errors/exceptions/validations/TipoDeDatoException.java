package com.bcba.nosisensolnet.errors.exceptions.validations;

import com.bcba.nosisensolnet.errors.exceptions.IErrorException;

public class TipoDeDatoException extends IErrorException {

    public TipoDeDatoException() {
        super();
    }

    public TipoDeDatoException(String... valores) {
        super(valores);
    }

    @Override
    public String getCodigo() {
        return this.propiedades.getTipoDeDatoCodigo();
    }

    @Override
    public String getDescripcion() {
        return this.propiedades.reemplazarParametros(propiedades.getTipoDeDatoDescripcion(), valores);
    }

}
