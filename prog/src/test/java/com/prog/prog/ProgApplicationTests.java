package com.prog.prog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import com.prog.prog.*

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class ProgApplicationTests {

    private PatrimoineService patrimoineService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, Patrimoine> patrimoineDB;

    @BeforeEach
    public void setUp() {
        patrimoineService = new PatrimoineService();
        patrimoineDB = new HashMap<>();

        Patrimoine patrimoine1 = new Patrimoine();
        patrimoine1.setPossession("Maison");
        patrimoine1.setDerniereModification(LocalDateTime.now());

        patrimoineDB.put("123", patrimoine1);
    }
	@Test
    public void testSavePatrimoines() throws IOException {
        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            patrimoineService.savePatrimoines(patrimoineDB);
            mockedFiles.verify(() -> Files.write(Paths.get("patrimoine.json"), objectMapper.writeValueAsBytes(patrimoineDB)));
        }
    }

    @Test
    public void testLoadPatrimoines() throws IOException {
        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.exists(Paths.get("patrimoine.json"))).thenReturn(true);
            mockedFiles.when(() -> Files.readAllBytes(Paths.get("patrimoine.json")))
                    .thenReturn(objectMapper.writeValueAsBytes(patrimoineDB));

            Map<String, Patrimoine> loadedPatrimoines = patrimoineService.loadPatrimoines();
            assertEquals(1, loadedPatrimoines.size());
            assertEquals("Maison", loadedPatrimoines.get("123").getPossession());
        }
    }

    @Test
    public void testLoadPatrimoinesFileNotFound() throws IOException {
        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.exists(Paths.get("patrimoine.json"))).thenReturn(false);

            Map<String, Patrimoine> loadedPatrimoines = patrimoineService.loadPatrimoines();
            assertTrue(loadedPatrimoines.isEmpty());
        }
    }

}
