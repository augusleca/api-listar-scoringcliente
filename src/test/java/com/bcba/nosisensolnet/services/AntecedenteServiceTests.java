//package com.bcba.nosisensolnet.services;
//
//import com.bcba.nosisensolnet.db.AntecedenteDB;
//import com.bcba.nosisensolnet.db.AntecedenteId;
//import com.bcba.nosisensolnet.models.AntecedenteModel;
//import com.bcba.nosisensolnet.repositories.AntecedenteRepository;
//import com.bcba.nosisensolnet.utils.Propiedades;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatCode;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class AntecedenteServiceTests {
//    @Mock
//    private ValidacionService validacionService;
//    @Mock
//    private AntecedenteRepository antecedenteRepository;
//    @Mock
//    private Propiedades propiedades;
//    @InjectMocks
//    private AntecedenteService antecedenteService;
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
//            1,
//            "descripcion tipo cliente",
//            BigDecimal.valueOf(50000),
//            2,
//            "descripcion subtipo cliente",
//            1
//        );
//    }
//
//    @Test
//    public void siFallaValidacionFallaElServicio() {
//        // dado
//        doThrow(RuntimeException.class).when(validacionService).validaciones(anyString());
//
//        // entonces
//        assertThatExceptionOfType(RuntimeException.class)
//            .isThrownBy(() -> antecedenteService.getAntecedente(cuitDefault));
//    }
//
//    @Test
//    public void siFallaRepositorioFallaElServicio() {
//        // dado
//        doThrow(RuntimeException.class).when(antecedenteRepository).findByCuit(anyString());
//
//        // entonces
//        assertThatExceptionOfType(RuntimeException.class)
//            .isThrownBy(() -> antecedenteService.getAntecedente(cuitDefault));
//    }
//
//    @Test
//    public void siNoEncuentraAntecedentesTiraExcepcion() {
//        // dado
//        when(antecedenteRepository.findByCuit(anyString()))
//            .thenReturn(Optional.empty());
//
//        // entonces
//        assertThatExceptionOfType(AntecedenteNotFoundException.class)
//            .isThrownBy(() -> antecedenteService.getAntecedente(cuitDefault));
//    }
//
//    @Test
//    public void siPasaValidacionesYEncuntraAntecedentesNoFallaElServicio() {
//        // dado
//        when(antecedenteRepository.findByCuit(anyString()))
//            .thenReturn(Optional.of(antecedenteDBDefault));
//        when(propiedades.getTipoAcreditacionDescripcion(anyInt()))
//            .thenReturn("");
//
//        // entonces
//        assertThatCode(() -> antecedenteService.getAntecedente(cuitDefault))
//            .doesNotThrowAnyException();
//    }
//
//    @Test
//    public void siPasaValidacionesYEncuntraAntecedentesLoDevuelve() {
//        // dado
//        when(antecedenteRepository.findByCuit(anyString()))
//            .thenReturn(Optional.of(antecedenteDBDefault));
//        when(propiedades.getTipoAcreditacionDescripcion(anyInt()))
//            .thenReturn("");
//
//        // cuando
//        AntecedenteModel response = antecedenteService.getAntecedente(cuitDefault);
//
//        // entonces
//        assertThat(response.getCuit()).isEqualTo(antecedenteDBDefault.getCuit());
//    }
//
//}
