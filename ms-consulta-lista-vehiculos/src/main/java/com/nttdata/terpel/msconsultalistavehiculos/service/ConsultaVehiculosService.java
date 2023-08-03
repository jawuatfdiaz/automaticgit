package com.nttdata.terpel.msconsultalistavehiculos.service;

import com.nttdata.terpel.commons.liblog.configuration.LoggerPrinter;
import org.springframework.http.ResponseEntity;

public interface ConsultaVehiculosService {

    ResponseEntity<Object> listarVehiculoMarca(String criterioBusqueda,String tipoVehiculo, LoggerPrinter loggerPrinter);
    ResponseEntity<Object> listarVehiculoPorLinea(String criterioBusqueda, String idVehiculo, String tipoVehiculo, LoggerPrinter loggerPrinter);
    ResponseEntity<Object> listarVehiculosPorCliente(String clienteId, LoggerPrinter loggerPrinter);
    ResponseEntity<Object> listarDatosVehiculoCliente(String clienteId, String vehiculoId, LoggerPrinter loggerPrinter);
}
