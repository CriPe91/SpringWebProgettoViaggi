package com.example.SpringWebProgetto.controller;

import com.example.SpringWebProgetto.dto.DipendenteDTO;
import com.example.SpringWebProgetto.entity.Dipendente;
import com.example.SpringWebProgetto.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    DipendenteService dipendenteService;

    // 1. - POST http://localhost:8080/dipendenti
    @PostMapping("/nuovoDipendente")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Dipendente saveDipendente(@RequestBody DipendenteDTO body) throws Exception {
        return dipendenteService.nuovoDipendente(body);
    }

    // 2. - GET http://localhost:8080/dipendenti
    @GetMapping("/ricercaDipendenti")
    public Page<Dipendente> getDipendenti(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return dipendenteService.getDipendenti(page, size, sortBy);
    }

    // 3. - GET http://localhost:8080/dipendenti/
    @GetMapping("/{id}")
    public Dipendente findById(@PathVariable long id) {
        return dipendenteService.findById(id);
    }

    // 4. - PUT http://localhost:8080/dipendenti/
    @PutMapping("/{id}")
    public Dipendente findAndUpdate(@PathVariable long id, @RequestBody Dipendente body) {
        return dipendenteService.findByIdAndUpdate(id, body);
    }

    // 5. - DELETE http://localhost:8080/dipendenti/
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable long id) {
        dipendenteService.findByIdAndDelete(id);
    }
}