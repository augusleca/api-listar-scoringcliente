//package com.example.demo;
//
//import com.example.demo.services.ClienteService;
//import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Disabled;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class CircuitBreakerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ClienteService clienteService;
//
//    @Autowired //cambiamos mockbean por autowired
//    private CircuitBreakerRegistry circuitBreakerRegistry;
//
//    @BeforeEach
//    void setUp() {
//        circuitBreakerRegistry.circuitBreaker("clenteCircuitBreaker").reset();
//    }
//
//    @Test
//    @Disabled
//    public void testCircuitBreakerCerradoYNoFallaRequest() throws Exception {
//        circuitBreakerRegistry.circuitBreaker("clienteCircuitBreaker").transitionToClosedState();
//        when(clienteService.getCliente(anyInt(), anyLong())).thenReturn(ResponseEntity.ok(null));
//
//        mockMvc.perform(get("/api/v1/clientes?tipoDocumento=25&nroDocumento=18289120")
//                .with(httpBasic("admin", "password")))
//            .andExpect(status().isOk());
//    }
//
//    @Test
//    @Disabled
//    public void testCircuitBreakerCerradoYFallaRequest() throws Exception {
//        circuitBreakerRegistry.circuitBreaker("clienteCircuitBreaker").transitionToClosedState();
//        when(clienteService.getCliente(anyInt(), anyLong())).thenThrow(RuntimeException.class);
//        when(clienteService.fallbackCircuitBreaker(any(Exception.class))).thenReturn(ResponseEntity.internalServerError().build());
//
//        mockMvc.perform(get("/api/v1/clientes?tipoDocumento=25&nroDocumento=18289120")
//                .with(httpBasic("admin", "password")))
//            .andExpect(status().isInternalServerError());
//    }
//
//    @Test
//    @Disabled
//    public void testCircuitBreakerAbierto() throws Exception {
//        circuitBreakerRegistry.circuitBreaker("clienteCircuitBreaker").transitionToOpenState();
//        when(clienteService.fallbackCircuitBreaker(any(Exception.class))).thenReturn(ResponseEntity.internalServerError().build());
//
//        mockMvc.perform(get("/api/v1/clientes?tipoDocumento=25&nroDocumento=18289120")
//                .with(httpBasic("admin", "password")))
//            .andExpect(status().isInternalServerError());
//    }
//}
