package com.example.SpringWebProgetto.service;

import com.example.SpringWebProgetto.dto.PrenotazioniDTO;
import com.example.SpringWebProgetto.entity.Prenotazioni;
import com.example.SpringWebProgetto.exception.NotFoundException;
import com.example.SpringWebProgetto.repository.PrenotazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioniService {
    @Autowired
    PrenotazioniRepository prenotazioneRepo;


    public Page<PrenotazioniDTO> getAllPrenotazioni(Pageable page) {
        Page<Prenotazioni> listaPrenotazioni = prenotazioneRepo.findAll(page);
        List<PrenotazioniDTO> listaDto = new ArrayList<>();

        for (Prenotazioni prenotazione : listaPrenotazioni.getContent()) {
            PrenotazioniDTO dto = fromPrenotazioneToDTO(prenotazione);
            listaDto.add(dto);
        }
        Page<PrenotazioniDTO> listaPage = new PageImpl<>(listaDto);
        return listaPage;
    }

    public String deletePrenotazione(long id) {
        Optional<Prenotazioni> prenotazioneTrovato = prenotazioneRepo.findById(id);
        if (prenotazioneTrovato.isPresent()) {
            prenotazioneRepo.delete(prenotazioneTrovato.get());
            return "Prenotazione con id: " + id + " eliminato con successo!";
        } else {
            throw new NotFoundException("Errore nella cancellazione! Nessuna prenotazione trovata con id: " + id);
        }

    }

    //  Da Prenotazione a PrenotazioneDTO
    public PrenotazioniDTO fromPrenotazioneToDTO(Prenotazioni prenotazione) {
        PrenotazioniDTO prenotazioneDTO = new PrenotazioniDTO();
        prenotazioneDTO.setDipendente(prenotazione.getDipendente());
        prenotazioneDTO.setViaggio(prenotazione.getViaggio());
        prenotazioneDTO.setData(prenotazione.getData());
        prenotazioneDTO.setNotePreferenzeDipendente(prenotazione.getNotePreferenzeDipendente());
        return prenotazioneDTO;
    }
}

