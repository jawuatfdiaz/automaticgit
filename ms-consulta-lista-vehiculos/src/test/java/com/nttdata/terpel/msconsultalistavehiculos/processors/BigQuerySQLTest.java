package com.nttdata.terpel.msconsultalistavehiculos.processors;

import com.google.cloud.bigquery.TableResult;
import com.nttdata.terpel.commons.liblog.configuration.LoggerPrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static com.nttdata.terpel.msconsultalistavehiculos.model.constant.Constantes.SELECT;
import static com.nttdata.terpel.msconsultalistavehiculos.model.constant.Constantes.WHERE;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class BigQuerySQLTest {

    @InjectMocks
    private BigQuerySQL bigQuerySQL;

    @Mock
    private LoggerPrinter loggerPrinter;

    private final String credencial = "C:\\Users\\jdiazpol\\Downloads\\service_account.json";

    private final String project = "terpel-gtic-datalake";

    @BeforeEach
    public void before() {
        /* Inicializar Objetos */
        loggerPrinter = new LoggerPrinter("132", "demo", "demo", "demo");

        ReflectionTestUtils.setField(bigQuerySQL, "credentialsPath", credencial);
        ReflectionTestUtils.setField(bigQuerySQL, "project", project);
    }

    @Test
    void testProceso() {
        TableResult result = bigQuerySQL.proceso(SELECT + "\n" +
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
                "                 LIMIT 10", loggerPrinter);
        Assertions.assertNotNull(result);
    }
}
