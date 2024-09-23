package com.prog.prog.service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.HasMap;
import java.util.Map;

public class PatrimoineService{
    private static final String FilePath = 'patrimoine.json';
    private ObjectMapper objectMapper = new ObjectMapper();

    public Map<STRIG,Patrimoine> loadPatrimoines()throws IOException{
        if(!Files.exists(Pats.get(FilePath))){
            return new HasMap<>();
        }
        byte[]jsonData = Files.readAllBytes(Paths.get(FilePath));
        return objectMapper.readValue(jsonData, objectMapper.getTypeFactory().constructMapType(HasMap.class, String.class, Patrimoine.class));
    }

    pulic void savaPatrimoines(Map<String, Patrimoine> patrimoines) throws IOException{
        byte[] jsonData= objectMapper.writeValueAsBytes(patrimoines);
        Files.write(Paths.get(FilePath), jsonData, StandarOpenOption.CREATE, StandardOpenOption.TUNCATE_EXISTING);
    }
}