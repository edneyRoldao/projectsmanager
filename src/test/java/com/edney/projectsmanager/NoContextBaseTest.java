package com.edney.projectsmanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class NoContextBaseTest {

    protected static ObjectMapper objectMapper;
    private final ClassLoader cl = this.getClass().getClassLoader();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeAll
    static void init() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SneakyThrows
    protected <T> T loadFile(String jsonPath, Class<T> t) {
        String json = readFile(jsonPath);
        return objectMapper.readValue(json, t);
    }

    @SneakyThrows
    protected <T> List<T> loadFile(String jsonPath) {
        String json = readFile(jsonPath);
        return objectMapper.readValue(json, new TypeReference<>() {});
    }

    private String readFile(String jsonPath) {
        String path = String.format("mocks/%s.json", jsonPath);
        var file = new File(requireNonNull(cl.getResource(path)).getPath());
        return readFileAsString(file);
    }

    @SneakyThrows
    public static String readFileAsString(File file) {
        FileInputStream in = FileUtils.openInputStream(file);
        return IOUtils.toString(in, StandardCharsets.UTF_8);
    }

}
