package com.bcba.nosisensolnet.errors.exceptions.resilience;

import com.bcba.nosisensolnet.errors.exceptions.IErrorException;

public class CircuitBreakerException extends IErrorException {

    public CircuitBreakerException() {
        super();
    }

    public CircuitBreakerException(String... valores) {
        super(valores);
    }

    @Override
    public String getCodigo() {
        return this.propiedades.getCircuitBreakerCodigo();
    }

    @Override
    public String getDescripcion() {
        return this.propiedades.reemplazarParametros(propiedades.getCircuitBreakerDescripcion(), valores);
    }

}
