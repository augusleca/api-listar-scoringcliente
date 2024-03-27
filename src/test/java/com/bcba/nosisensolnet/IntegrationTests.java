//package com.bcba.nosisensolnet;
//
//import com.bcba.nosisensolnet.db.AntecedenteDB;
//import com.bcba.nosisensolnet.db.AntecedenteId;
//import com.bcba.nosisensolnet.repositories.AntecedenteRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.dao.DataRetrievalFailureException;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ActiveProfiles("test")
//@SpringBootTest
//@AutoConfigureMockMvc
//@EnableAutoConfiguration(exclude = {
//    DataSourceAutoConfiguration.class,
//    DataSourceTransactionManagerAutoConfiguration.class,
//    HibernateJpaAutoConfiguration.class})
//public class IntegrationTests {
//
//    @Autowired
//    MockMvc mockMvc;
//    @MockBean
//    AntecedenteRepository antecedenteRepository;
//
//    @Value("${properties.endpoint}")
//    private String endpoint;
//
//    @Value("${properties.exceptions.tipo.codigo}")
//    private String tipoExceptionCodigo;
//    @Value("${properties.exceptions.longitud.codigo}")
//    private String longitudExceptionCodigo;
//    @Value("${properties.exceptions.circuitBreaker.codigo}")
//    private String circuitBreakerExceptionCodigo;
//    @Value("${properties.exceptions.db.codigo}")
//    private String dbExceptionCodigo;
//
//
//    private final String cuitDefault = "20266444096";
//
//    private final AntecedenteDB antecedenteDBDefault = crearAntecedenteDBValido();
//
//    private AntecedenteDB crearAntecedenteDBValido() {
//        return new AntecedenteDB(
//            AntecedenteId.of(cuitDefault),
//            LocalDate.of(2023, 9, 10),
//            0,
//            2,
//            "Cliente Plan Sueldo / Jubilados y Pensionados",
//            BigDecimal.valueOf(800000.23),
//            2,
//            "Acredita Haberes",
//            2
//            );
//    }
//
//    @Test
//    public void siLaRequestEsCorrectaLaResponseEsCorrecta() throws Exception {
//        // dado
//        when(antecedenteRepository.findByCuit(cuitDefault)).thenReturn(Optional.of(antecedenteDBDefault));
//
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", cuitDefault)
//            )
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.cuit").value(cuitDefault))
//            .andExpect(jsonPath("$.fechaHasta").value("2023-09-10"))
//            .andExpect(jsonPath("$.tieneAntecedentesDesfavorables").value(0))
//            .andExpect(jsonPath("$.tipoClienteCodigo").value(2))
//            .andExpect(jsonPath("$.tipoClienteDescripcion").value("Cliente Plan Sueldo / Jubilados y Pensionados"))
//            .andExpect(jsonPath("$.ingresosHaberes").value(800000.23))
//            .andExpect(jsonPath("$.subTipoClienteCodigo").value(2))
//            .andExpect(jsonPath("$.subTipoClienteDescripcion").value("Acredita Haberes"))
//            .andExpect(jsonPath("$.tipoAcreditacion").value(2))
//        ;
//    }
//
//    @Test
//    public void siFaltaElCuitDevuelveNotFound() throws Exception {
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/")
//            )
//            .andExpect(status().isNotFound())
//        ;
//    }
//
//    @Test
//    public void siElTipoDelCuitNoEsUnNumericoDevuelveErrorDeTipo() throws Exception {
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", cuitDefault + "a")
//            )
//            .andExpect(status().isBadRequest())
//            .andExpect(jsonPath("$.codigo").value(tipoExceptionCodigo))
//            .andExpect(jsonPath("$.descripcion").exists())
//            .andExpect(jsonPath("$.errores").exists())
//            .andExpect(jsonPath("$.errores[0].cuit0003").exists())
//        ;
//    }
//
//    @Test
//    public void siElCuitEsDeMenorLongitudDevuelveErrorDeLongitud() throws Exception {
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", "2026644409")
//            )
//            .andExpect(status().isBadRequest())
//            .andExpect(jsonPath("$.codigo").value(longitudExceptionCodigo))
//            .andExpect(jsonPath("$.descripcion").exists())
//            .andExpect(jsonPath("$.errores").hasJsonPath())
//        ;
//    }
//
//    @Test
//    public void siNoEncuentraElCuitEnLaDBDevuelveQueNoTieneAntecedentes() throws Exception {
//        // dado
//        when(antecedenteRepository.findByCuit(anyString())).thenReturn(Optional.empty());
//
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", cuitDefault)
//            )
//            .andExpect(status().isOk())
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
//    public void siSurgeUnErrorInesperadoLoAgarraElCircuitBreakerYDevuelveStatus503() throws Exception {
//        // dado
//        when(antecedenteRepository.findByCuit(anyString())).thenThrow(RuntimeException.class);
//
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", cuitDefault)
//            )
//            .andExpect(status().isServiceUnavailable())
//        ;
//    }
//
//    @Test
//    public void siSurgeUnErrorInesperadoLoAgarraElCircuitBreakerYDevuelveErrorPersonalizado() throws Exception {
//        // dado
//        when(antecedenteRepository.findByCuit(anyString())).thenThrow(RuntimeException.class);
//
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", cuitDefault)
//            )
//            .andExpect(jsonPath("$.codigo").value(circuitBreakerExceptionCodigo))
//            .andExpect(jsonPath("$.descripcion").exists())
//            .andExpect(jsonPath("$.errores").hasJsonPath())
//        ;
//    }
//
//    @Test
//    public void siSurgeUnErrorEnLaBaseDatosSeDevuelveErrorDeDB() throws Exception {
//        // dado
//        when(antecedenteRepository.findByCuit(anyString())).thenThrow(DataRetrievalFailureException.class);
//
//        // entonces
//        mockMvc.perform(
//                get(endpoint + "/{cuit}", cuitDefault)
//            )
//            .andExpect(jsonPath("$.codigo").value(dbExceptionCodigo))
//            .andExpect(jsonPath("$.descripcion").exists())
//            .andExpect(jsonPath("$.errores").hasJsonPath())
//        ;
//    }
//
//}