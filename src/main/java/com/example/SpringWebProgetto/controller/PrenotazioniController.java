package com.example.SpringWebProgetto.controller;

import com.example.SpringWebProgetto.dto.PrenotazioniDTO;
import com.example.SpringWebProgetto.service.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazione")
public class PrenotazioniController {
    @Autowired
    PrenotazioniService prenotazioneService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Page<PrenotazioniDTO>> getAllPrenotazioni(Pageable page) {
        Page<PrenotazioniDTO> prenotazioni = prenotazioneService.getAllPrenotazioni(page);
        return new ResponseEntity<>(prenotazioni, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePrenotazione(@PathVariable long id) {
        return new ResponseEntity<>(prenotazioneService.deletePrenotazione(id), HttpStatus.OK);
    }
}
