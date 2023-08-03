//package com.nttdata.terpel.msconsultalistavehiculos.processors;
//
//import com.google.api.gax.paging.Page;
//import com.google.cloud.PageImpl;
//import com.google.cloud.bigquery.*;
//import com.google.common.collect.ImmutableList;
//import com.nttdata.terpel.commons.liblog.configuration.LoggerPrinter;
//import com.nttdata.terpel.msconsultalistavehiculos.model.response.ListasMarcas;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.util.List;
//import java.util.Map;
//
//import static com.google.cloud.bigquery.FieldValue.Attribute.PRIMITIVE;
//
//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
//class MapeoFiltrosTest {
//
//    @InjectMocks
//    MapeoFiltros mapeoFiltros;
//
//    private LoggerPrinter loggerPrinter;
//    private static final Page<FieldValueList> INNER_PAGE_0 =
//            new PageImpl<>(
//                    new PageImpl.NextPageFetcher<FieldValueList>() {
//                        @Override
//                        public Page<FieldValueList> getNextPage() {
//                            return INNER_PAGE_1;
//                        }
//                    },
//                    "abc",
//                    ImmutableList.of(newFieldValueList("0"), newFieldValueList("1")));
//    private static final Page<FieldValueList> INNER_PAGE_1 =
//            new PageImpl<>(
//                    new PageImpl.NextPageFetcher<FieldValueList>() {
//                        @Override
//                        public Page<FieldValueList> getNextPage() {
//                            return null;
//                        }
//                    },
//                    null,
//                    ImmutableList.of(newFieldValueList("2")));
//
//    private static FieldValueList newFieldValueList(String s) {
//        return FieldValueList.of(ImmutableList.of(FieldValue.of(PRIMITIVE, s), FieldValue.of(PRIMITIVE, s), FieldValue.of(PRIMITIVE, s), FieldValue.of(PRIMITIVE, s), FieldValue.of(PRIMITIVE, s), FieldValue.of(PRIMITIVE, s)));
//    }
//
//    private static final Schema SCHEMA = Schema.of(Field.of("field", LegacySQLTypeName.INTEGER), Field.of("field", LegacySQLTypeName.INTEGER), Field.of("field", LegacySQLTypeName.INTEGER), Field.of("field", LegacySQLTypeName.INTEGER), Field.of("field", LegacySQLTypeName.INTEGER), Field.of("field", LegacySQLTypeName.INTEGER));
//
//    private String result = "{rows=[[FieldValue{attribute=PRIMITIVE, value=325}, FieldValue{attribute=PRIMITIVE, " +
//            "value=a4R04000000HVVIEA4}, FieldValue{attribute=PRIMITIVE, value=CHEVROLET}]], " +
//            "schema=Schema{fields=[Field{name=dup_count, type=INTEGER, mode=NULLABLE, description=null, " +
//            "policyTags=null, maxLength=null, scale=null, precision=null, defaultValueExpression=null}, " +
//            "Field{name=Vehiculos_por_Marca, type=STRING, mode=NULLABLE, description=null, policyTags=null, " +
//            "maxLength=null, scale=null, precision=null, defaultValueExpression=null}, Field{name=Name, type=STRING, " +
//            "mode=NULLABLE, description=null, policyTags=null, maxLength=null, scale=null, precision=null, " +
//            "defaultValueExpression=null}]}, totalRows=1, cursor=null}";
//
//    //TableResult result = new TableResult(SCHEMA, 3, INNER_PAGE_0);
//    @BeforeEach
//    public void before() {
//        /* Inicializar Objetos */
//        loggerPrinter = new LoggerPrinter("132", "demo", "demo", "demo");
//
//        //ReflectionTestUtils.setField( mapeoFiltros,"TableResult", result);
//
//    }
//
//    TableResult resultado = new TableResult(SCHEMA, 3, INNER_PAGE_0);
//
//
//    @Test
//    void testFiltroCargaInicialListaMarcas() {
//        ListasMarcas result = mapeoFiltros.filtroCargaInicialListaMarcas(resultado, loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testFiltroListasVehiculo() {
//        List<Map<String, String>> result = mapeoFiltros.filtroListasVehiculo(resultado, loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testFiltroVehiculosCliente() {
//        List<Map<String, String>> result = mapeoFiltros.filtroVehiculosCliente(resultado, loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    void testFiltroDatosVehiculoCliente() {
//        List<Map<String, String>> result = mapeoFiltros.filtroDatosVehiculoCliente(resultado, loggerPrinter);
//        Assertions.assertNotNull(result);
//    }
//}
//
