package com.prog.prog.controller;
import com.prog.prog.service.PatrimoineService;
import com.prog.prog.service.PatrimoineService.*;
import com.prog.prog.model.patrimoine;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.HasMap;
import java.util.Map;

@RestController
@RequetMapping('/patrimoine')
public class PatrimoineController{
    private PatrimoineService service = new PatrimoineService();

    @PutMapping('/{id}')
    public Patrimoine updatePatrimoine(@PathVariable String id, @RequetBody Patrimoine patrimoine) throws IOException{
        Map<String, Patrimoine> patrimoineDB = service.loadPatrimoines();

        patrimoine.setDerniereModification(LocalDateTime.now());
        patrimoineDB.put(id,patrimoine);

        service.savaPatrimoines(patrimoineDB);
        return patrimoine
    }

}