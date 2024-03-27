package com.bcba.nosisensolnet.errors;

import com.bcba.nosisensolnet.errors.exceptions.resilience.CircuitBreakerException;
import com.bcba.nosisensolnet.errors.exceptions.IErrorException;
import com.bcba.nosisensolnet.errors.exceptions.resilience.RateLimiterException;
import com.bcba.nosisensolnet.models.ErrorModel;
import com.bcba.nosisensolnet.utils.Propiedades;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Propiedades propiedades;

    @Autowired
    public GlobalExceptionHandler(Propiedades propiedades) {
        this.propiedades = propiedades;
    }

    @ExceptionHandler(IErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorModel manejoExcepcionPersonalizada(IErrorException ex) {
        return ex.getErrorModel(propiedades);
    }

    @ExceptionHandler(CircuitBreakerException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ErrorModel manejoCircuitBreakerException(CircuitBreakerException ex) {
        log.error("Se abrio el Circuit breaker");
        return ex.getErrorModel(propiedades);
    }

    @ExceptionHandler(RateLimiterException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    @ResponseBody
    public ErrorModel manejoRateLimiterException(RateLimiterException ex) {
        log.warn("Se activo el Rate limiter");
        return ex.getErrorModel(propiedades);
    }

}
