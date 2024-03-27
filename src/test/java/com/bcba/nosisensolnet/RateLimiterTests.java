//package com.example.demo;
//
//import com.example.demo.db.cliente.ClienteId;
//import io.github.resilience4j.ratelimiter.RateLimiter;
//import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.RepeatedTest;
//import org.junit.jupiter.api.RepetitionInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class RateLimiterTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private RateLimiterRegistry rateLimiterRegistry;
//
//    @MockBean
//    private ClienteRepository clienteRepository;
//
//    private ClienteDB createClienteDB(int tipoDocumento, long nroDocumento) {
//        return new ClienteDB(ClienteId.of(tipoDocumento, nroDocumento), "razonSocial", 1134434565, 1155556666, 432534, "algo@gmail.com", "M");
//    }
//
//    @BeforeEach
//    public void setUp() {
//        // Por las dudas
//        when(clienteRepository.findById(ClienteId.of(25, 18289120))).thenReturn(Optional.empty());
//        RateLimiter rateLimiterTest = rateLimiterRegistry.rateLimiter("clienteRateLimiter");
//        rateLimiterTest.changeLimitForPeriod(11);
//    }
//
//    @Test
//    @Disabled
//    void testRateLimitOk() throws Exception {
//        int tipoDocumento = 25;
//        int nroDocumento = 18289120;
////        ClienteDB clienteTest = createClienteDB(tipoDocumento, nroDocumento);
////        when(clienteRepository.find(tipoDocumento, nroDocumento))
////                .thenReturn(Optional.of(clienteTest));
//
//        mockMvc.perform(get("/api/v1/clientes?tipoDocumento=25&nroDocumento=18289120")
//                        .with(httpBasic("admin", "password")))
//                .andExpect(status().isOk());
//    }
//
//    @RepeatedTest(11)
//    @Disabled
//    public void testRateLimiterSuperado(RepetitionInfo repetitionInfo) throws Exception {
//
//        ResultMatcher result = repetitionInfo.getCurrentRepetition() == 11 ? status().isTooManyRequests() : status().isNotFound();
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clientes?tipoDocumento=25&nroDocumento=18289120")
//                .with(httpBasic("admin", "password")))
//                .andExpect(result);
//    }
//
//}
