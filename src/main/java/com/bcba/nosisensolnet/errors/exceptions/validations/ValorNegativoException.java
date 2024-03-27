package com.bcba.nosisensolnet.errors.exceptions.validations;

import com.bcba.nosisensolnet.errors.exceptions.IErrorException;

public class ValorNegativoException extends IErrorException {

    public ValorNegativoException() {
        super();
    }

    public ValorNegativoException(String... valores) {
        super(valores);
    }

    @Override
    public String getCodigo() {
        return this.propiedades.getValorNegativoCodigo();
    }

    @Override
    public String getDescripcion() {
        return this.propiedades.reemplazarParametros(propiedades.getValorNegativoDescripcion(), valores);
    }

}
