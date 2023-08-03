//package com.nttdata.terpel.msconsultalistavehiculos.service.impl;
//
//import com.nttdata.terpel.commons.liblog.configuration.LoggerPrinter;
//import com.nttdata.terpel.msconsultalistavehiculos.bigQuery.ListaVehiculosBigQuery;
//import com.nttdata.terpel.msconsultalistavehiculos.model.ResponseRest;
//import com.nttdata.terpel.msconsultalistavehiculos.processors.BigQuerySQL;
//import com.nttdata.terpel.msconsultalistavehiculos.processors.MapeoFiltros;
//import com.nttdata.terpel.msconsultalistavehiculos.processors.ResponseError;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.slf4j.Logger;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
//class ConsultaVehiculosServiceImplTest {
//    @Mock
//    ResponseError error;
//    @Mock
//    BigQuerySQL querySql;
//    @Mock
//    MapeoFiltros map;
//    @Mock
//    ListaVehiculosBigQuery bigQuery;
//    @Mock
//    Logger log;
//    @InjectMocks
//    ConsultaVehiculosServiceImpl consultaVehiculosServiceImpl;
//
//    private LoggerPrinter loggerPrinter;
//
//    @BeforeEach
//    public void before() {
//        /* Inicializar Objetos */
//        loggerPrinter = new LoggerPrinter("132", "demo", "demo", "demo");
//    }
//
//    @Test
//    void testListarVehiculoMarca() {
//
//        when(querySql.proceso(anyString(), any())).thenReturn(null);
//        when(map.filtroCargaInicialListaMarcas(any(), any())).thenReturn(List.of(Map.of("String", "String")));
//        when(bigQuery.getConsultarVehiculosPorMarca(anyString(), anyString())).thenReturn("getConsultarVehiculosPorMarcaResponse");
//
//        ResponseEntity<Object> result = consultaVehiculosServiceImpl.listarVehiculoMarca("criterioBusqueda", "tipoVehiculo", loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testListarVehiculoMarcaTipoVehiculoNulo() {
//        ResponseEntity<Object> result = consultaVehiculosServiceImpl.listarVehiculoMarca( "","tipoVehiculo", loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testListarVehiculoMarcaError() {
//        when(error.error(anyString(), anyString())).thenReturn(new ResponseRest());
//        ResponseEntity<Object> result = consultaVehiculosServiceImpl.listarVehiculoMarca( "criterioBusqueda","", loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testListarVehiculoPorLinea() {
//        when(querySql.proceso(anyString(), any())).thenReturn(null);
//        when(map.filtroListasVehiculo(any(), any())).thenReturn(List.of(Map.of("String", "String")));
//        when(bigQuery.getConsultaVehiculosPorLinea(anyString(), anyString(), anyString())).thenReturn("getConsultaVehiculosPorLineaResponse");
//
//        ResponseEntity<Object> result = consultaVehiculosServiceImpl.listarVehiculoPorLinea("criterioBusqueda", "idVehiculo", "tipoVehiculo", loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testListarVehiculoPorLineaCriterioBusquedaNulo() {
//
//        ResponseEntity<Object> result = consultaVehiculosServiceImpl.listarVehiculoPorLinea(null, "idVehiculo", "tipoVehiculo", loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testListarVehiculoPorLineaTipoVehiculoNulo() {
//        when(error.error(anyString(), anyString())).thenReturn(new ResponseRest());
//        ResponseEntity<Object> result = consultaVehiculosServiceImpl.listarVehiculoPorLinea("criterioBusqueda", "idVehiculo", "", loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testListarVehiculoPorLineaIdVehiculoNulo() {
//        when(error.error(anyString(), anyString())).thenReturn(new ResponseRest());
//        ResponseEntity<Object> result = consultaVehiculosServiceImpl.listarVehiculoPorLinea("criterioBusqueda", "", "tipoVehiculo", loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//
//    @Test
//    void testListarVehiculosPorCliente() {
//        when(querySql.proceso(anyString(), any())).thenReturn(null);
//        when(map.filtroVehiculosCliente(any(), any())).thenReturn(List.of(Map.of("String", "String")));
//        when(bigQuery.getConsultarVehiculosPorCliente(anyString())).thenReturn("getConsultarVehiculosPorClienteResponse");
//
//        ResponseEntity<Object> result = consultaVehiculosServiceImpl.listarVehiculosPorCliente("clienteId", loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testListarVehiculosPorClienteNulo() {
//        when(error.error(anyString(), anyString())).thenReturn(new ResponseRest());
//        ResponseEntity<Object> result = consultaVehiculosServiceImpl.listarVehiculosPorCliente("", loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testListarDatosVehiculoCliente() {
//        when(querySql.proceso(anyString(), any())).thenReturn(null);
//        when(map.filtroDatosVehiculoCliente(any(), any())).thenReturn(List.of(Map.of("String", "String")));
//        when(bigQuery.getConsultarDatosVehiculoCliente(anyString(), anyString())).thenReturn("getConsultarDatosVehiculoClienteResponse");
//
//        ResponseEntity<Object> result = consultaVehiculosServiceImpl.listarDatosVehiculoCliente("clienteId", "vehiculoId", loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testListarDatosVehiculoClienteNuloClienteId() {
//        when(error.error(anyString(), anyString())).thenReturn(new ResponseRest());
//
//        ResponseEntity<Object> result = consultaVehiculosServiceImpl.listarDatosVehiculoCliente("", "vehiculoId", loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testListarDatosVehiculoClienteNuloVehiculoId() {
//        when(error.error(anyString(), anyString())).thenReturn(new ResponseRest());
//
//        ResponseEntity<Object> result = consultaVehiculosServiceImpl.listarDatosVehiculoCliente("clienteId", "", loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//}
