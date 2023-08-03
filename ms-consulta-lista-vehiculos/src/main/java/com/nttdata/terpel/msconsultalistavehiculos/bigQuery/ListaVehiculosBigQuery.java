package com.nttdata.terpel.msconsultalistavehiculos.bigQuery;

import org.springframework.stereotype.Component;

import static com.nttdata.terpel.msconsultalistavehiculos.model.constant.Constantes.*;

@Component
public class ListaVehiculosBigQuery {

    private static final String CONSULTA_INICIAL_CARGA_LISTA_MARCA = SELECT + "\n" +
            "                 cont.dup_count,\n" +
            "                 cont.Vehiculos_por_Marca,\n" +
            "                 n.Name FROM (( \n" +
            "                 SELECT COUNT(*) OVER(PARTITION BY Vehiculos_por_Marca) AS dup_count,\n" +
            "                 Vehiculos_por_Marca\n" +
            "                 FROM\n" +
            "                 `terpel-gtic-datalake.club_gazel_pruebas_refinada.VehiculospoMarca` \n" +
            WHERE + "\n" +
            "                 Modelo = true AND TipodeVehiculo = '${headers.tipoVehiculo}') AS cont\n" +
            "                 INNER JOIN\n" +
            "                 (SELECT\n" +
            "                 Name,\n" +
            "                 Id\n" +
            "                 FROM `terpel-gtic-datalake.club_gazel_pruebas_refinada.VehiculospoMarca`\n" +
            WHERE + "\n" +
            "                 Marca = true) AS n ON n.Id = cont.Vehiculos_por_Marca )\n" +
            WHERE + "\n" +
            "                 cont.dup_count >= 1\n" +
            "                 GROUP BY\n" +
            "                 cont.Vehiculos_por_Marca,\n" +
            "                 cont.dup_count,\n" +
            "                 n.Name\n" +
            "                 ORDER BY cont.dup_count\n" +
            "                 DESC\n" +
            "                 LIMIT 10";

    private static final String CONSULTA_VEHICULOS_POR_MARCA = SELECT + "\n" +
            "cont.dup_count,\n" +
            "cont.Vehiculos_por_Marca,\n" +
            "n.Name \n" +
            "FROM \n" +
            "((SELECT COUNT(*) OVER(PARTITION BY Vehiculos_por_Marca) AS dup_count,\n" +
            "Vehiculos_por_Marca\n" +
            "FROM `terpel-gtic-datalake.club_gazel_pruebas_refinada.VehiculospoMarca` \n" +
            WHERE + "\n" +
            "Modelo = true AND TipodeVehiculo = '${headers.tipoVehiculo}') AS cont\n" +
            "INNER JOIN \n" +
            "(SELECT\n" +
            "Name,\n" +
            "Id\n" +
            "FROM `terpel-gtic-datalake.club_gazel_pruebas_refinada.VehiculospoMarca` \n" +
            WHERE + "\n" +
            "Marca = true ) AS n " +
            "ON\n" +
            "n.Id = cont.Vehiculos_por_Marca )\n" +
            WHERE + "\n" +
            "cont.dup_count >= 1 \n" +
            AND + "\n" +
            "n.Name\n" +
            "LIKE '%${headers.criterioBusqueda}%'\n" +
            "GROUP BY\n" +
            "cont.Vehiculos_por_Marca,\n" +
            "cont.dup_count,\n" +
            "n.Name\n" +
            "ORDER BY\n" +
            "cont.dup_count\n" +
            "DESC\n" +
            "LIMIT 10";

    private static final String CONSULTA_VEHICULOS_POR_LINEA = SELECT + "\n"
            + "Id,\n"
            + "Name\n"
            + "FROM `terpel-gtic-datalake.club_gazel_pruebas_refinada.VehiculospoMarca`\n"
            + "WHERE\n"
            + "Name\n"
            + "LIKE '%${headers.criterioBusqueda}%'\n"
            + AND + "\n"
            + "Modelo= true\n"
            + "AND\n"
            + "Vehiculos_por_Marca='${headers.idVehiculo}'\n"
            + AND + "\n"
            + "TipodeVehiculo = '${headers.tipoVehiculo}'\n"
            + "LIMIT 10";

    private static final String CONSULTA_VEHICULOS_POR_CLIENTE = SELECT + "\n"
            + "placa,\n"
            + "id,\n"
            + "TipoVehiculo\n"
            + "FROM `terpel-gtic-datalake.club_gazel_pruebas_refinada.Vehiculos`\n"
            + "WHERE\n"
            + "cliente='${headers.clienteId}'\n"
            + AND + "\n"
            + "activo = true";

    private static final String CONSULTA_DATOS_VEHICULO_CLIENTE = "SELECT\n"
            + "vm.Name AS Marca,\n"
            + "vpm.Name AS Modelo,\n"
            + "v.AnioVehiculo,\n"
            + "v.Placa AS Placa,\n"
            + "v.TipoVehiculo AS TipoVehiculo,\n"
            + "v.TipoMotor AS TipoMotor\n"
            + "FROM\n"
            + "`terpel-gtic-datalake.club_gazel_pruebas_refinada.Vehiculos` v\n"
            + "INNER JOIN\n"
            + "`terpel-gtic-datalake.club_gazel_pruebas_refinada.VehiculospoMarca` vm\n"
            + "ON\n"
            + "v.VehiculosMarca = vm.id\n"
            + "INNER JOIN\n"
            + "`terpel-gtic-datalake.club_gazel_pruebas_refinada.VehiculospoMarca` vpm\n"
            + "ON\n"
            + "v.VehiculosModelo = vpm.id\n"
            + AND + "\n"
            + "cliente='${headers.clienteId}'\n"
            + AND + "\n"
            + "v.Id = '${headers.vehiculoId}'";

    public String getConsultaInicialCargaListaMarca(String tipoVehiculo) {
        return CONSULTA_INICIAL_CARGA_LISTA_MARCA.replace(HEADERS_TIPO_VEHICULO, tipoVehiculo);
    }

    public String getConsultarVehiculosPorMarca(String criterioBusqueda, String tipoVehiculo) {

        return CONSULTA_VEHICULOS_POR_MARCA.replace(HEADERS_TIPO_VEHICULO, tipoVehiculo)
                .replace(HEADERS_CRITERIO_BUSQUEDA, criterioBusqueda);
    }


    public String getConsultaVehiculosPorLineaNulo(String idVehiculo, String tipoVehiculo) {
        return CONSULTA_VEHICULOS_POR_LINEA.replace(HEADERS_CRITERIO_BUSQUEDA, VACIO)
                .replace(HEADERS_TIPO_VEHICULO, tipoVehiculo).replace(HEADERS_ID_VEHICULO, idVehiculo);
    }

    public String getConsultaVehiculosPorLinea(String criterioBusqueda, String idVehiculo, String tipoVehiculo) {
        return CONSULTA_VEHICULOS_POR_LINEA.replace(HEADERS_CRITERIO_BUSQUEDA, criterioBusqueda)
                .replace(HEADERS_TIPO_VEHICULO, tipoVehiculo)
                .replace(HEADERS_ID_VEHICULO, idVehiculo);
    }

    public String getConsultarVehiculosPorCliente(String clienteId) {
        return CONSULTA_VEHICULOS_POR_CLIENTE.replace(HEADERS_CLIENTE_ID, clienteId);
    }

    public String getConsultarDatosVehiculoCliente(String clienteId, String vehiculoId) {
        return CONSULTA_DATOS_VEHICULO_CLIENTE.replace(HEADERS_CLIENTE_ID, clienteId)
                .replace(HEADERS_VEHICULO_ID, vehiculoId);

    }
}
