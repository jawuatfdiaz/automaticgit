package com.nttdata.terpel.msconsultalistavehiculos.model.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class Constantes {

    //Error
    public static final String DATA = "C0000AD60009198A0000009E0000544C";
    public static final String REQUESTTIME = "2021-07-14T07:50:08.523";
    public static final String APIVERSION = "1.0";

    //Data
    public static final String NEGOCIO_MASTER_LUB = "MasterLub";
    public static final String CANAL_WEB = "WEB";
    public static final String WHERE = "WHERE";
    public static final String SELECT = "SELECT";
    public static final String AND =  "AND";
    public static final String VACIO =  "";
    public static final String CANTIDAD =  "cantidad";
    public static final String ID =  "id";
    public static final String NOMBRE_MARCA =  "nombreMarca";
    public static final String PLACA =  "placa";
    public static final String MARCA =  "marca";
    public static final String LINEA =  "linea";
    public static final String ANIO =  "anio";
    public static final String TIPO_MOTOR =  "tipoMotor";
    public static final String TIPO_VEHICULO =  "tipoVehiculo";
    public static final String HEADERS_TIPO_VEHICULO = "${headers.tipoVehiculo}";
    public static final String HEADERS_CRITERIO_BUSQUEDA = "${headers.criterioBusqueda}";
    public static final String HEADERS_ID_VEHICULO = "${headers.idVehiculo}";
    public static final String HEADERS_CLIENTE_ID = "${headers.clienteId}";
    public static final String HEADERS_VEHICULO_ID = "${headers.vehiculoId}";
    public static final String CRITERIO_BUSQUEDA = "criterioBusqueda: ";
    public static final String VEHICULO = " tipoVehiculo: ";
    public static final String ID_VEHICULO = " idVehiculo: ";
    public static final String CLIENTE_ID = "clienteId: ";
    public static final String VEHICULO_ID = " vehiculoId: ";
    public static final String CONSULTA = "Consulta: ";
    public static final String DATOS = "data: ";

    //mensajes
    public static final String JOB_NO_EXISTE = "Job no longer existe";
    public static final String MAP_DATA_BIGQUERY_LISTA_MARCA = "Mapeo data BigQuery lista marcas";




}