package com.example.SpringWebProgetto.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.SpringWebProgetto.dto.DipendenteDTO;
import com.example.SpringWebProgetto.dto.PrenotazioniDTO;
import com.example.SpringWebProgetto.service.DipendenteService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/dipendente")
public class DipendenteController {
    @Autowired
    DipendenteService dipendenteService;
    @Autowired
    Cloudinary cloudinary;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Page<DipendenteDTO>> getAllDipendenti(Pageable page) {
        Page<DipendenteDTO> dipendenti = dipendenteService.getAllDipendenti(page);
        return new ResponseEntity<>(dipendenti, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public DipendenteDTO getDipendenteById(@PathVariable long id) {
        return dipendenteService.findDipendenteById(id);

    }

    @PostMapping("")
    public ResponseEntity<?> nuovoDipendente(@RequestBody @Validated DipendenteDTO dipendenteDTO, BindingResult validation) {

        if (validation.hasErrors()) {
            String errorMessage = "ERRORE DI VALIDAZIONE \n";

            for (ObjectError errore : validation.getAllErrors()) {
                errorMessage += errore.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        Long idNuovoDipendente = dipendenteService.saveDipendente(dipendenteDTO);
        return new ResponseEntity<>("Dipendente inserito nel DB con id: " + idNuovoDipendente, HttpStatus.CREATED);
    }

    @PostMapping("/imgProfilo")
    public ResponseEntity<?> nuovoDipendenteConImmagine(@RequestPart("profilePic") MultipartFile profilePic, @RequestPart @Validated DipendenteDTO dipendenteDTO, BindingResult validation) {

        if (validation.hasErrors()) {
            String errorMessage = "ERRORE DI VALIDAZIONE \n";

            for (ObjectError errore : validation.getAllErrors()) {
                errorMessage += errore.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        try {

            Map mappa = cloudinary.uploader().upload(profilePic.getBytes(), ObjectUtils.emptyMap());
            String urlImage = mappa.get("secure_url").toString();
            dipendenteDTO.setImgProfilo(urlImage);

            Long idNuovoDipendente = dipendenteService.saveDipendente(dipendenteDTO);
            return new ResponseEntity<>("Dipendente inserito nel DB con id: " + idNuovoDipendente, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento dell'immagine: " + e);
        }


    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDipendente(@RequestBody @Validated DipendenteDTO dipendenteDTO, BindingResult validation, @PathVariable long id) {
        if (validation.hasErrors()) {
            String errorMessage = "ERRORE DI VALIDAZIONE \n";

            for (ObjectError errore : validation.getAllErrors()) {
                errorMessage += errore.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        } else {
            dipendenteService.updateDipendente(dipendenteDTO, id);
            return new ResponseEntity<>("Il dipendente è stato modificato correttamente", HttpStatus.OK);
        }
    }

    @PostMapping("/newReservation")
    public ResponseEntity<?> creaNuovaPrenotazione(@RequestBody @Validated PrenotazioniDTO prenotazioneDTO, BindingResult validation) throws BadRequestException {
        if (validation.hasErrors()) {
            String errorMessage = "ERRORE DI VALIDAZIONE \n";

            for (ObjectError errore : validation.getAllErrors()) {
                errorMessage += errore.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        } else {
            dipendenteService.creaPrenotazione(prenotazioneDTO);
            return new ResponseEntity<>("La prenotazione è stata creata correttamente", HttpStatus.OK);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDipendente(@PathVariable long id) {
        return new ResponseEntity<>(dipendenteService.deleteDipendente(id), HttpStatus.OK);
    }
}


