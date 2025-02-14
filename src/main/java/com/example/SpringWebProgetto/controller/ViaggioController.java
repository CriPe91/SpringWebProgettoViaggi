package com.example.SpringWebProgetto.controller;

import com.example.SpringWebProgetto.dto.ViaggioDTO;
import com.example.SpringWebProgetto.entity.Viaggio;
import com.example.SpringWebProgetto.service.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    ViaggioService viaggioService;

    // 1. - POST http://localhost:8080/viaggi/nuovoViaggio
    @PostMapping("/nuovoViaggio")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Viaggio nuovoViaggio(@RequestBody ViaggioDTO body) throws Exception {
        return viaggioService.nuovoViaggio(body);
    }

    // 2. - GET http://localhost:8080/viaggi
    @GetMapping("/ricercaViaggi")
    public Page<Viaggio> getViaggi(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return viaggioService.getViaggi(page, size, sortBy);
    }

    // 3. - GET http://localhost:8080/viaggi/{id}
    @GetMapping("/{id}")
    public Viaggio findById(@PathVariable long id) {
        return viaggioService.findById(id);
    }

    // 4. - PUT http://localhost:8080/viaggi/{id}
    @PutMapping("/{id}")
    public Viaggio findAndUpdate(@PathVariable long id, @RequestBody Viaggio body) {
        return viaggioService.findByIdAndUpdate(id, body);
    }

    // 5. - DELETE http://localhost:8080/viaggi/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable long id) {
        viaggioService.findByIdAndDelete(id);
    }
}