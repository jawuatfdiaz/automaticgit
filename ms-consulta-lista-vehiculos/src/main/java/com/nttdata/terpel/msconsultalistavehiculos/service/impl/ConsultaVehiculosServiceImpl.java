package com.nttdata.terpel.msconsultalistavehiculos.service.impl;

import com.google.cloud.bigquery.TableResult;
import com.nttdata.terpel.commons.liblog.configuration.LoggerPrinter;
import com.nttdata.terpel.commons.liblog.util.ProcessType;
import com.nttdata.terpel.msconsultalistavehiculos.model.response.*;
import com.nttdata.terpel.msconsultalistavehiculos.processors.BigQuerySQL;
import com.nttdata.terpel.msconsultalistavehiculos.processors.ResponseError;
import com.nttdata.terpel.msconsultalistavehiculos.bigQuery.ListaVehiculosBigQuery;
import com.nttdata.terpel.msconsultalistavehiculos.service.ConsultaVehiculosService;
import com.nttdata.terpel.msconsultalistavehiculos.processors.MapeoFiltros;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.nttdata.terpel.commons.liblog.util.LoggerConstant.VALOR_NA;
import static com.nttdata.terpel.msconsultalistavehiculos.model.constant.Constantes.*;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConsultaVehiculosServiceImpl implements ConsultaVehiculosService {

    private final ResponseError error;

    private final BigQuerySQL querySql;

    private final MapeoFiltros map;

    private final ListaVehiculosBigQuery bigQuery;

    @SneakyThrows
    @Override
    public ResponseEntity<Object> listarVehiculoMarca(String criterioBusqueda, String tipoVehiculo,
                                                      LoggerPrinter loggerPrinter) {

        if (Objects.isNull(tipoVehiculo) || tipoVehiculo.isBlank()) {

            loggerPrinter.log(LogLevel.ERROR, "Error inesperado tipoVehiculo obligatorio",
                    CRITERIO_BUSQUEDA + criterioBusqueda + VEHICULO + tipoVehiculo,
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(), VALOR_NA, ProcessType.SALIDA);
            return new ResponseEntity<>(error.error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    "Ha ocurrido un error Inesperado tipoVehiculo es obligatorio"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (Objects.isNull(criterioBusqueda) || criterioBusqueda.isBlank()) {

            loggerPrinter.log(LogLevel.INFO, "BigQuery Consulta lista marcas sin criterioBusqueda",
                    VEHICULO + tipoVehiculo, VALOR_NA, VALOR_NA, ProcessType.PROCESO);
            String consulta = bigQuery.getConsultaInicialCargaListaMarca(tipoVehiculo);

            TableResult bigQuery1 = querySql.proceso(consulta, loggerPrinter);

            ListasMarcas mapeo1 = map.filtroCargaInicialListaMarcas(bigQuery1, loggerPrinter);
            loggerPrinter.log(LogLevel.INFO, "Fin lista vehiculo por marca",
                    CRITERIO_BUSQUEDA + criterioBusqueda + VEHICULO + tipoVehiculo,
                    HttpStatus.OK.toString(), VALOR_NA, ProcessType.SALIDA);
            return new ResponseEntity<>(mapeo1, HttpStatus.OK);

        } else {
            loggerPrinter.log(LogLevel.INFO, "BigQuery Consulta lista marcas",
                    CRITERIO_BUSQUEDA + criterioBusqueda + VEHICULO + tipoVehiculo, VALOR_NA,
                    VALOR_NA, ProcessType.PROCESO);
            String consulta = bigQuery.getConsultarVehiculosPorMarca(criterioBusqueda, tipoVehiculo);

            TableResult bigQuery1 = querySql.proceso(consulta, loggerPrinter);
            ListasMarcas mapeo1 = map.filtroCargaInicialListaMarcas(bigQuery1, loggerPrinter);

            loggerPrinter.log(LogLevel.INFO, "Fin lista vehiculo por marca",
                    CRITERIO_BUSQUEDA + criterioBusqueda + VEHICULO + tipoVehiculo,
                    HttpStatus.OK.toString(), VALOR_NA, ProcessType.SALIDA);
            return new ResponseEntity<>(mapeo1, HttpStatus.OK);
        }
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Object> listarVehiculoPorLinea(String criterioBusqueda, String idVehiculo,
                                                         String tipoVehiculo, LoggerPrinter loggerPrinter) {

        if (Objects.isNull(tipoVehiculo) || tipoVehiculo.isBlank()) {

            loggerPrinter.log(LogLevel.ERROR, "Error inesperado tipoVehiculo obligatorio",
                    CRITERIO_BUSQUEDA + criterioBusqueda + ID_VEHICULO + idVehiculo + VEHICULO + tipoVehiculo,
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(), VALOR_NA, ProcessType.SALIDA);
            return new ResponseEntity<>(error.error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    "Ha ocurrido un error Inesperado tipoVehiculo es obligatorio"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (Objects.isNull(idVehiculo) || idVehiculo.isBlank()) {

            loggerPrinter.log(LogLevel.ERROR, "Error inesperado idVehiculo obligatorio",
                    CRITERIO_BUSQUEDA + criterioBusqueda + ID_VEHICULO + idVehiculo + VEHICULO + tipoVehiculo,
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(), VALOR_NA, ProcessType.SALIDA);
            return new ResponseEntity<>(error.error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    "Ha ocurrido un error Inesperado idVehiculo es obligatorio"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (Objects.isNull(criterioBusqueda)) {

            loggerPrinter.log(LogLevel.INFO, "BigQuery Consulta vehiculo por linea sin criterioBusqueda",
                    ID_VEHICULO + idVehiculo + VEHICULO + tipoVehiculo, VALOR_NA,
                    VALOR_NA, ProcessType.PROCESO);
            String consulta = bigQuery.getConsultaVehiculosPorLineaNulo(idVehiculo, tipoVehiculo);

            TableResult bigQuery2 = querySql.proceso(consulta, loggerPrinter);
            List<ListaVehiculo> mapeo2 = map.filtroListasVehiculo(bigQuery2, loggerPrinter);

            loggerPrinter.log(LogLevel.INFO, "Fin lista vehiculo por linea",
                    ID_VEHICULO + idVehiculo + VEHICULO + tipoVehiculo,
                    HttpStatus.OK.toString(), VALOR_NA, ProcessType.SALIDA);
            return new ResponseEntity<>(mapeo2, HttpStatus.OK);
        } else {

            loggerPrinter.log(LogLevel.INFO, "BigQuery Consulta vehiculo por linea",
                    CRITERIO_BUSQUEDA + criterioBusqueda + ID_VEHICULO + idVehiculo + VEHICULO + tipoVehiculo,
                    VALOR_NA, VALOR_NA, ProcessType.PROCESO);
            String consulta = bigQuery.getConsultaVehiculosPorLinea(criterioBusqueda, idVehiculo, tipoVehiculo);

            TableResult bigQuery2 = querySql.proceso(consulta, loggerPrinter);
            List<ListaVehiculo> mapeo2 = map.filtroListasVehiculo(bigQuery2, loggerPrinter);

            loggerPrinter.log(LogLevel.INFO, "Fin lista vehiculo por linea",
                    CRITERIO_BUSQUEDA + criterioBusqueda + ID_VEHICULO + idVehiculo + VEHICULO + tipoVehiculo,
                    HttpStatus.OK.toString(), VALOR_NA, ProcessType.SALIDA);
            return new ResponseEntity<>(mapeo2, HttpStatus.OK);
        }
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Object> listarVehiculosPorCliente(String clienteId, LoggerPrinter loggerPrinter) {

        if (Objects.isNull(clienteId) || clienteId.isBlank()) {

            loggerPrinter.log(LogLevel.ERROR, "Error inesperado clienteId obligatorio",
                    CLIENTE_ID + clienteId, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    VALOR_NA, ProcessType.SALIDA);
            return new ResponseEntity<>(error.error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    "Ha ocurrido un error Inesperado clienteId es obligatorio"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            loggerPrinter.log(LogLevel.INFO, "BigQuery vehiculos por cliente",
                    CLIENTE_ID + clienteId, VALOR_NA, VALOR_NA, ProcessType.PROCESO);
            String consulta = bigQuery.getConsultarVehiculosPorCliente(clienteId);

            TableResult bigQuery3 = querySql.proceso(consulta, loggerPrinter);
            ListarVehiculoCliente mapeo3 = map.filtroVehiculosCliente(bigQuery3, loggerPrinter);

            loggerPrinter.log(LogLevel.INFO, "Fin listar vehiculos por cliente",
                    CLIENTE_ID + clienteId, HttpStatus.OK.toString(), VALOR_NA, ProcessType.SALIDA);
            return new ResponseEntity<>(mapeo3, HttpStatus.OK);
        }
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Object> listarDatosVehiculoCliente(String clienteId, String vehiculoId,
                                                             LoggerPrinter loggerPrinter) {
        if (Objects.isNull(clienteId) || clienteId.isBlank()) {

            loggerPrinter.log(LogLevel.ERROR, "Error inesperado clienteId obligatorio",
                    CLIENTE_ID + clienteId + VEHICULO_ID + vehiculoId,
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(), VALOR_NA, ProcessType.SALIDA);
            return new ResponseEntity<>(error.error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    "Ha ocurrido un error Inesperado clienteId es obligatorio"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (Objects.isNull(vehiculoId) || vehiculoId.isBlank()) {

            loggerPrinter.log(LogLevel.ERROR, "Error inesperado vehiculoId obligatorio",
                    CLIENTE_ID + clienteId + VEHICULO_ID + vehiculoId,
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(), VALOR_NA, ProcessType.SALIDA);
            return new ResponseEntity<>(error.error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    "Ha ocurrido un error Inesperado vehiculoId es obligatorio"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else {

            loggerPrinter.log(LogLevel.INFO, "BigQuery datos vehiculo cliente",
                    CLIENTE_ID+clienteId+VEHICULO_ID+vehiculoId, VALOR_NA, VALOR_NA, ProcessType.PROCESO);
            String consulta = bigQuery.getConsultarDatosVehiculoCliente(clienteId, vehiculoId);

            TableResult bigQuery4 = querySql.proceso(consulta, loggerPrinter);
            DatosVehiculo mapeo4 = map.filtroDatosVehiculoCliente(bigQuery4, loggerPrinter);

            loggerPrinter.log(LogLevel.INFO, "Fin lista datos vehiculo cliente",
                    CLIENTE_ID+clienteId+VEHICULO_ID+vehiculoId, HttpStatus.OK.toString(), VALOR_NA, ProcessType.SALIDA);
            return new ResponseEntity<>(mapeo4, HttpStatus.OK);
        }
    }
}
