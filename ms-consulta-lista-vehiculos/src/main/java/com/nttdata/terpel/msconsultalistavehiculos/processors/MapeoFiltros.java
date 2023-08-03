package com.nttdata.terpel.msconsultalistavehiculos.processors;

import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;
import com.nttdata.terpel.commons.liblog.configuration.LoggerPrinter;
import com.nttdata.terpel.commons.liblog.util.ProcessType;
import com.nttdata.terpel.msconsultalistavehiculos.model.response.*;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nttdata.terpel.commons.liblog.util.LoggerConstant.VALOR_NA;
import static com.nttdata.terpel.msconsultalistavehiculos.model.constant.Constantes.*;

@Component
public class MapeoFiltros {

    public ListasMarcas filtroCargaInicialListaMarcas(TableResult data, LoggerPrinter loggerPrinter) {

        loggerPrinter.log(LogLevel.INFO, MAP_DATA_BIGQUERY_LISTA_MARCA,
                DATOS + data, VALOR_NA, VALOR_NA, ProcessType.PROCESO);

        ListasMarcas listasMarcas = new ListasMarcas();

        // Print all pages of the results.
        for (FieldValueList row : data.iterateAll()) {

            listasMarcas.setCantidad(row.get(0).getStringValue());
            listasMarcas.setId(row.get(1).getStringValue());
            listasMarcas.setNombreMarca(row.get(2).getStringValue());

        }
        return listasMarcas;
    }

    public List<ListaVehiculo> filtroListasVehiculo(TableResult data, LoggerPrinter loggerPrinter) {

        loggerPrinter.log(LogLevel.INFO, "Mapeo data BigQuery lista vehiculos",
                DATOS + data, VALOR_NA, VALOR_NA, ProcessType.PROCESO);
        ListaVehiculo listaVehiculo = new ListaVehiculo();
        List<ListaVehiculo> vehiculos = new ArrayList<>();

    // Print all pages of the results.
        for (FieldValueList row : data.iterateAll()) {

            listaVehiculo.setId(row.get(0).getStringValue());
            listaVehiculo.setNombreMarca(row.get(1).getStringValue());

            vehiculos.add(listaVehiculo);
        }
        return vehiculos;

    }

    public ListarVehiculoCliente filtroVehiculosCliente(TableResult data, LoggerPrinter loggerPrinter) {

        loggerPrinter.log(LogLevel.INFO, "Mapeo data BigQuery vehiculos cliente",
                DATOS + data, VALOR_NA, VALOR_NA, ProcessType.PROCESO);
        ListarVehiculoCliente vehiculoCliente = new ListarVehiculoCliente();
        // Print all pages of the results.
        for (FieldValueList row : data.iterateAll()) {

           vehiculoCliente.setPlaca(row.get(0).getStringValue());
           vehiculoCliente.setId(row.get(1).getStringValue());
           vehiculoCliente.setTipoVehiculo(row.get(2).getStringValue());
        }
        return vehiculoCliente;
    }

    public DatosVehiculo filtroDatosVehiculoCliente(TableResult data, LoggerPrinter loggerPrinter) {

        loggerPrinter.log(LogLevel.INFO, "Mapeo data BigQuery datos vehiculo cliente",
                DATOS + data, VALOR_NA, VALOR_NA, ProcessType.PROCESO);
        DatosVehiculo datosVehiculo = new DatosVehiculo();
        // Print all pages of the results.
        for (FieldValueList row : data.iterateAll()) {

            datosVehiculo.setMarca(row.get(0).getStringValue());
            datosVehiculo.setLinea(row.get(1).getStringValue());
            datosVehiculo.setAnio(row.get(2).getStringValue());
            datosVehiculo.setPlaca(row.get(3).getStringValue());
            datosVehiculo.setTipoVehiculo(row.get(4).getStringValue());
            datosVehiculo.setTipoMotor(row.get(5).getStringValue());

        }

        return datosVehiculo;

    }
}
