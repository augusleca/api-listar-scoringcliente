package com.bcba.nosisensolnet.controllers;

import com.bcba.nosisensolnet.errors.exceptions.resilience.CircuitBreakerException;
import com.bcba.nosisensolnet.errors.exceptions.resilience.RateLimiterException;
import com.bcba.nosisensolnet.services.InformeService;
import com.bcba.nosisensolnet.errors.exceptions.IErrorException;
import com.bcba.nosisensolnet.services.ScoreInformeRequest;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RequestMapping("/api/${properties.version}/utils/informe/nosis-en-solnet")
@CrossOrigin(originPatterns = "*")
@RestController
public class InformeController implements IInformeController {

    private final InformeService informeService;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    public InformeController(InformeService informeService, CircuitBreakerRegistry circuitBreakerRegistry) {
        this.informeService = informeService;
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    @CircuitBreaker(name = "circuitBreaker", fallbackMethod = "fallbackCircuitBreaker")
    @Retry(name = "retry")
    @RateLimiter(name = "rateLimiter", fallbackMethod = "fallbackRateLimiter")
    @GetMapping("/score-nosis")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody

    public ResponseEntity<Map<String, Object>> getScoreInforme(
        @RequestParam String cuit,
        @RequestParam String canal,
        @RequestParam String modelo,
        @RequestParam Integer campana) {

            ScoreInformeRequest request = new ScoreInformeRequest();
            request.setCuit(cuit);
            request.setCanal(canal);
            request.setModelo(modelo);
            request.setCampana(campana);

            Map<String, Object> resultado = informeService.getScoreInforme(request);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    // fallbacks
    public String fallbackCircuitBreaker(String cuit, Exception exception) throws Exception {
        ArrayList<Class> ignoredExceptions = new ArrayList<>();
        ignoredExceptions.add(DataAccessException.class);
        ignoredExceptions.add(IErrorException.class);
        ignoredExceptions.add(RateLimiterException.class);

        if (ignoredExceptions.stream().anyMatch(ex -> ex.isAssignableFrom(exception.getClass()))) {
            circuitBreakerRegistry.circuitBreaker("circuitBreaker").transitionToClosedState();
            throw exception;
        }

        throw new CircuitBreakerException();
    }

    public String fallbackRateLimiter(String cuit, RequestNotPermitted exception) {
        throw new RateLimiterException();
    }
}
