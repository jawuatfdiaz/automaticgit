package com.nttdata.terpel.msconsultalistavehiculos.controller;

import com.nttdata.terpel.commons.liblog.configuration.LoggerPrinter;
import com.nttdata.terpel.commons.liblog.util.ProcessType;
import com.nttdata.terpel.msconsultalistavehiculos.service.ConsultaVehiculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

import static com.nttdata.terpel.commons.liblog.util.LoggerConstant.VALOR_NA;
import static com.nttdata.terpel.msconsultalistavehiculos.model.constant.Constantes.*;

@Controller
@RequestMapping(path = "${service.controller.path}" + "/listasVehiculo")
public class ConsultaVehiculosController {

    @Autowired
    ConsultaVehiculosService consultaVehiculosService;

    LoggerPrinter loggerPrinter = new LoggerPrinter(NEGOCIO_MASTER_LUB, UUID.randomUUID().toString(),
            CANAL_WEB, VALOR_NA);

    @GetMapping(path = "/marca", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> listarVehiculoMarca(@RequestParam("criterioBusqueda") String criterioBusqueda,
                                                      @RequestParam("tipoVehiculo") String tipoVehiculo) {

        loggerPrinter.log(LogLevel.INFO, "Inicia lista vehiculo por marca",
                CRITERIO_BUSQUEDA + criterioBusqueda + VEHICULO + tipoVehiculo,
                HttpStatus.ACCEPTED.toString(), VALOR_NA, ProcessType.ENTRADA);
        return consultaVehiculosService.listarVehiculoMarca(criterioBusqueda, tipoVehiculo, loggerPrinter);
    }

    @GetMapping(path = "/linea", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> listarVehiculoPorLinea(@RequestParam("criterioBusqueda") String criterioBusqueda,
                                                         @RequestParam("idVehiculo") String idVehiculo,
                                                         @RequestParam("tipoVehiculo") String tipoVehiculo) {

        loggerPrinter.log(LogLevel.INFO, "Inicia lista vehiculo por linea",
                CRITERIO_BUSQUEDA + criterioBusqueda + ID_VEHICULO + idVehiculo + VEHICULO + tipoVehiculo,
                HttpStatus.ACCEPTED.toString(), VALOR_NA, ProcessType.ENTRADA);
        return consultaVehiculosService.listarVehiculoPorLinea(criterioBusqueda, idVehiculo, tipoVehiculo,
                loggerPrinter);
    }

    @GetMapping(path = "/vehiculosPorCliente", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> listarVehiculosPorCliente(@RequestParam("clienteId") String clienteId) {

        loggerPrinter.log(LogLevel.INFO, "Inicia lista vehiculos por cliente", CLIENTE_ID+clienteId,
                HttpStatus.ACCEPTED.toString(), VALOR_NA, ProcessType.ENTRADA);
        return consultaVehiculosService.listarVehiculosPorCliente(clienteId, loggerPrinter);
    }

    @GetMapping(path = "/datosVehiculoCliente", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> listarDatosVehiculoCliente(@RequestParam("clienteId") String clienteId,
                                                             @RequestParam("vehiculoId") String vehiculoId) {

        loggerPrinter.log(LogLevel.INFO, "Inicia lista datos vehiculo cliente",
                CLIENTE_ID+clienteId+VEHICULO_ID+vehiculoId,
                HttpStatus.ACCEPTED.toString(), VALOR_NA, ProcessType.ENTRADA);
        return consultaVehiculosService.listarDatosVehiculoCliente(clienteId, vehiculoId, loggerPrinter);
    }

}
