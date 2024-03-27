//package com.bcba.nosisensolnet.controllers;
//
//import com.bcba.nosisensolnet.errors.exceptions.LongitudException;
//import com.bcba.nosisensolnet.models.AntecedenteModel;
//import com.bcba.nosisensolnet.services.AntecedenteService;
//import com.bcba.nosisensolnet.utils.Propiedades;
//import io.github.resilience4j.springboot3.circuitbreaker.autoconfigure.CircuitBreakerAutoConfiguration;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ActiveProfiles("test")
//@WebMvcTest(AntecedenteController.class)
//@Import({Propiedades.class, CircuitBreakerAutoConfiguration.class})
//public class AntecedenteControllerTests {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    AntecedenteService antecedenteService;
//
//    @Value("${properties.endpoint}")
//    private String endpoint;
//
//    private final String cuitDefault = "20266444096";
//
//    private final AntecedenteModel antecedenteDefault = crearAntecedenteModelValido();
//
//    private AntecedenteModel crearAntecedenteModelValido() {
//        return AntecedenteModel.builder()
//            .cuit(cuitDefault)
//            .fechaHasta(LocalDate.of(2023, 9, 10))
//            .tieneAntecedentesDesfavorables(0)
//            .tipoClienteCodigo(2)
//            .tipoClienteDescripcion("Cliente Plan Sueldo / Jubilados y Pensionados")
//            .ingresosHaberes(BigDecimal.valueOf(800000.23))
//            .subTipoClienteCodigo(2)
//            .subTipoClienteDescripcion("Acredita Haberes")
//            .tipoAcreditacion(2)
//            .tipoAcreditacionDescripcion("Jubilacion")
//            .build();
//    }
//
//    @Test
//    public void unaRequestCorrectaDevuelveStatus200() throws Exception {
//        // dado
//        when(antecedenteService.getAntecedente(anyString())).thenReturn(antecedenteDefault);
//
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", cuitDefault)
//            )
//            .andExpect(status().isOk())
//        ;
//    }
//
//    @Test
//    public void unaRequestCorrectaDevuelveInfoDeAntecedentesCorrecta() throws Exception {
//        // dado
//        when(antecedenteService.getAntecedente(anyString())).thenReturn(antecedenteDefault);
//
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", cuitDefault)
//            )
//            .andExpect(jsonPath("$.cuit").value(cuitDefault))
//            .andExpect(jsonPath("$.fechaHasta").exists())
//            .andExpect(jsonPath("$.tieneAntecedentesDesfavorables").exists())
//            .andExpect(jsonPath("$.tipoClienteCodigo").exists())
//            .andExpect(jsonPath("$.tipoClienteDescripcion").exists())
//            .andExpect(jsonPath("$.ingresosHaberes").exists())
//            .andExpect(jsonPath("$.subTipoClienteCodigo").exists())
//            .andExpect(jsonPath("$.subTipoClienteDescripcion").exists())
//            .andExpect(jsonPath("$.tipoAcreditacion").exists())
//            .andExpect(jsonPath("$.tipoAcreditacionDescripcion").exists());
//    }
//
//    @Test
//    public void siNoSeEncuentranAntcedentesDevuelveStatus200() throws Exception {
//        // dado
//        when(antecedenteService.getAntecedente(anyString())).thenThrow(new AntecedenteNotFoundException(cuitDefault));
//
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", cuitDefault)
//            )
//            .andExpect(status().isOk())
//        ;
//    }
//
//    @Test
//    public void siNoSeEncuentranAntecedentesDevuelveUnaRespuesta() throws Exception {
//        // dado
//        when(antecedenteService.getAntecedente(anyString())).thenThrow(new AntecedenteNotFoundException(cuitDefault));
//
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", cuitDefault)
//            )
//            .andExpect(jsonPath("$.cuit").value(cuitDefault))
//            .andExpect(jsonPath("$.fechaHasta").value(LocalDate.now().toString()))
//            .andExpect(jsonPath("$.tieneAntecedentesDesfavorables").value(0))
//            .andExpect(jsonPath("$.tipoClienteCodigo").doesNotExist())
//            .andExpect(jsonPath("$.tipoClienteDescripcion").doesNotExist())
//            .andExpect(jsonPath("$.ingresosHaberes").doesNotExist())
//            .andExpect(jsonPath("$.subTipoClienteCodigo").doesNotExist())
//            .andExpect(jsonPath("$.subTipoClienteDescripcion").doesNotExist())
//            .andExpect(jsonPath("$.tipoAcreditacion").doesNotExist())
//        ;
//    }
//
//    @Test
//    public void siNoPasaUnaValidacionDevuelveStatus400() throws Exception {
//        // dado
//        when(antecedenteService.getAntecedente(anyString())).thenThrow(LongitudException.class);
//
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", cuitDefault)
//            )
//            .andExpect(status().isBadRequest())
//        ;
//    }
//
//    @Test
//    public void siNoPasaUnaValidacionDevuelveUnErrorPersonalizado() throws Exception {
//        // dado
//        when(antecedenteService.getAntecedente(anyString())).thenThrow(new LongitudException());
//
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", cuitDefault)
//            )
//            .andExpect(jsonPath("$.codigo").exists())
//            .andExpect(jsonPath("$.descripcion").exists())
//            .andExpect(jsonPath("$.errores").hasJsonPath())
//        ;
//    }
//
//}