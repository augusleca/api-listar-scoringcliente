//package com.bcba.nosisensolnet.services;
//
//import com.bcba.nosisensolnet.errors.exceptions.LongitudException;
//import com.bcba.nosisensolnet.errors.exceptions.TipoException;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThatCode;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//
//public class ValidacionServiceTests {
//
//    private final ValidacionService validacionService = new ValidacionService();
//
//    private final String cuitDefault = "20266444096"; // 11 caracteres numericos
//
//    @Test
//    public void validacionesDefaultPasanCorrectamente() {
//        // entonces
//        assertThatCode(() -> validacionService.validaciones(cuitDefault))
//            .doesNotThrowAnyException();
//    }
//
//    @Test
//    public void validacionTipoDeDato() {
//        // dado
//        String cuitIncorrecto = "2026644096b"; // Con caracter no numerico
//
//        // entonces
//        assertThatExceptionOfType(TipoException.class)
//            .isThrownBy(() -> validacionService.validaciones(cuitIncorrecto));
//    }
//
//    @Test
//    public void validacionLongitud() {
//        // dado
//        String cuitIncorrecto = "2026644409"; // 10 caracteres numericos
//
//        // entonces
//        assertThatExceptionOfType(LongitudException.class)
//            .isThrownBy(() -> validacionService.validaciones(cuitIncorrecto));
//    }
//
//}
