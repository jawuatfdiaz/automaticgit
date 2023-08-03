package com.nttdata.terpel.msconsultalistavehiculos.processors;

import com.nttdata.terpel.msconsultalistavehiculos.model.MetaInformacion;
import com.nttdata.terpel.msconsultalistavehiculos.model.ResponseRest;
import org.springframework.stereotype.Component;

import static com.nttdata.terpel.msconsultalistavehiculos.model.constant.Constantes.*;

@Component
public class ResponseError {

    public ResponseRest error(String codigoError, String mensaje) {

        return ResponseRest.builder()
                .code(codigoError)
                .message(mensaje)
                .data(DATA)
                .meta(MetaInformacion
                        .builder()
                        .requestTime(REQUESTTIME)
                        .apiVersion(APIVERSION)
                        .build())
                .build();
    }
}
