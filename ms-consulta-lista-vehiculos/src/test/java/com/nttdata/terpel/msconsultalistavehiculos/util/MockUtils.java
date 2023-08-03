package com.nttdata.terpel.msconsultalistavehiculos.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * The type Mock utils.
 */
public class MockUtils {
    /**
     * Gets mock.
     *
     * @param <T>   the type parameter
     * @param path  the path
     * @param clazz the clazz
     * @return the mock
     * @throws IOException the io exception
     */
    public static <T> T getMock(String path, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource classPathResource = new ClassPathResource(path);
        return objectMapper.readValue(classPathResource.getInputStream(), clazz);
    }
}
