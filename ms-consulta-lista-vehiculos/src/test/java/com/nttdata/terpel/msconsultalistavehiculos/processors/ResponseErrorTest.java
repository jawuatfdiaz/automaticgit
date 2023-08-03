package com.nttdata.terpel.msconsultalistavehiculos.processors;

import com.nttdata.terpel.msconsultalistavehiculos.model.ResponseRest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ResponseErrorTest {
    ResponseError responseError = new ResponseError();

    @Test
    void testError() {
        ResponseRest result = responseError.error("codigoError", "mensaje");
        Assertions.assertNotNull(result);
    }
}
