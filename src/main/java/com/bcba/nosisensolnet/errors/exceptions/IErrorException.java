package com.bcba.nosisensolnet.errors.exceptions;

import com.bcba.nosisensolnet.models.ErrorModel;
import com.bcba.nosisensolnet.utils.Propiedades;

public abstract class IErrorException extends RuntimeException {

    protected Propiedades propiedades;

    protected String[] valores = new String[]{};

    public IErrorException() {
        super();
    }

    public IErrorException(String... valores) {
        super();
        this.valores = valores;
    }

    public abstract String getCodigo();

    public abstract String getDescripcion();

    public ErrorModel getErrorModel(Propiedades propiedades) {
        this.propiedades = propiedades;
        return new ErrorModel(this.getCodigo(), this.getDescripcion());
    }

}
