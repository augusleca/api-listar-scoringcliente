package com.bcba.nosisensolnet.errors.exceptions.resilience;

import com.bcba.nosisensolnet.errors.exceptions.IErrorException;

public class RateLimiterException extends IErrorException {

    public RateLimiterException() {
        super();
    }

    public RateLimiterException(String... valores) {
        super(valores);
    }

    @Override
    public String getCodigo() {
        return this.propiedades.getRateLimiterCodigo();
    }

    @Override
    public String getDescripcion() {
        return this.propiedades.reemplazarParametros(propiedades.getRateLimiterDescripcion(), valores);
    }

}
