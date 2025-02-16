package com.example.SpringWebProgetto.service;

import com.example.SpringWebProgetto.dto.DipendenteDTO;
import com.example.SpringWebProgetto.dto.PrenotazioniDTO;
import com.example.SpringWebProgetto.entity.Dipendente;
import com.example.SpringWebProgetto.entity.Prenotazioni;
import com.example.SpringWebProgetto.entity.Viaggio;
import com.example.SpringWebProgetto.exception.NotFoundException;
import com.example.SpringWebProgetto.repository.DipendenteRepository;
import com.example.SpringWebProgetto.repository.PrenotazioniRepository;
import com.example.SpringWebProgetto.repository.ViaggioRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DipendenteService {
    @Autowired
    DipendenteRepository dipendenteRepo;
    @Autowired
    ViaggioRepository viaggioRepo;
    @Autowired
    PrenotazioniRepository prenotazioneRepo;


    //salva dipendente
    public Long saveDipendente(DipendenteDTO dipendenteDTO) {
        Dipendente dipendenteInserito = dto_entity(dipendenteDTO);
        Dipendente dipendenteSalvato = dipendenteRepo.save(dipendenteInserito);
        return dipendenteSalvato.getId();
    }


    public Page<DipendenteDTO> getAllDipendenti(Pageable page) {
        Page<Dipendente> listaDipendenti = dipendenteRepo.findAll(page);
        List<DipendenteDTO> listaDto = new ArrayList<>();

        for (Dipendente dipendente : listaDipendenti.getContent()) {
            DipendenteDTO dto = entity_dto(dipendente);
            listaDto.add(dto);
        }

        Page<DipendenteDTO> pageList = new PageImpl<>(listaDto);
        return pageList;
    }

    // metodo da DipendenteRepository
    public DipendenteDTO findDipendenteById(long id) {
        Optional<Dipendente> dipendente = dipendenteRepo.findById(id);

        if (dipendente.isPresent()) {
            DipendenteDTO dipendenteDto = entity_dto(dipendente.get());
            return dipendenteDto;
        } else {
            throw new NotFoundException("Nessun dipendente trovato con l'id: " + id);
        }
    }

    // sostituisciDipendenteById
    public void updateDipendente(DipendenteDTO dipendenteDTO, long id) {
        Optional<Dipendente> dipendenteTrovato = dipendenteRepo.findById(id);

        if (dipendenteTrovato.isPresent()) {
            Dipendente dipendente = dipendenteTrovato.get();
            dipendente.setUsername(dipendenteDTO.getUsername());
            dipendente.setNome(dipendenteDTO.getNome());
            dipendente.setCognome(dipendenteDTO.getCognome());
            dipendente.setEmail(dipendenteDTO.getEmail());
            dipendente.setImgProfilo(dipendenteDTO.getImgProfilo());
            dipendenteRepo.save(dipendente);
        } else {
            throw new NotFoundException("Errore nella modifica del dipendente inserito. Dipendente non trovato!");
        }
    }

    //  cancella dipendente tramite id
    public String deleteDipendente(long id) {
        Optional<Dipendente> dipendenteTrovato = dipendenteRepo.findById(id);
        if (dipendenteTrovato.isPresent()) {
            dipendenteRepo.delete(dipendenteTrovato.get());
            return "Dipendente con id: " + id + " eliminato con successo!";
        } else {
            throw new NotFoundException("Errore nel delete! Nessun dipendente trovato con id: " + id);
        }

    }

    //  crea Prenotazione
    public void creaPrenotazione(PrenotazioniDTO prenotazioneDTO) throws BadRequestException {
        Optional<Dipendente> dipendenteTrovato = dipendenteRepo.findById(prenotazioneDTO.getDipendente().getId());
        Optional<Viaggio> viaggioTrovato = viaggioRepo.findById(prenotazioneDTO.getViaggio().getId());
        if (dipendenteTrovato.isPresent() && viaggioTrovato.isPresent()) {
            Dipendente dipendente = dipendenteTrovato.get();
            Viaggio viaggio = viaggioTrovato.get();
            LocalDate dataPrenotazione = prenotazioneDTO.getData();

            List<Prenotazioni> prenotazioniEsistentiConLaStessaData = prenotazioneRepo.findByDataAndDipendente(dataPrenotazione, dipendente);
            List<Prenotazioni> prenotazioniDelDipendente = prenotazioneRepo.findByDipendente(dipendente);
            // per la creazione della prenotazione, il dipendente non deve
            // avere altre prenotazioni con la stessa data
            if (!prenotazioniEsistentiConLaStessaData.isEmpty()) {
                throw new BadRequestException("Il dipendente ha gia una prenotazione per questa data");
            } else if (!prenotazioniDelDipendente.isEmpty()) {
                throw new BadRequestException("Il dipendente non può prenotare altri viaggi, perchè ha già prenotato un viaggio");
            } else {
                Prenotazioni prenotazione = new Prenotazioni();
                prenotazione.setDipendente(dipendente);
                prenotazione.setViaggio(viaggio);
                prenotazione.setData(prenotazioneDTO.getData());
                prenotazione.setNotePreferenzeDipendente(prenotazioneDTO.getNotePreferenzeDipendente());
                prenotazioneRepo.save(prenotazione);
            }
        } else {
            throw new NotFoundException(" L'id del viaggio o del dipendente non è stato trovato");
        }
    }


    //Dipendente a DipendenteDTO
    public Dipendente dto_entity(DipendenteDTO dipendenteDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(dipendenteDTO.getUsername());
        dipendente.setNome(dipendenteDTO.getNome());
        dipendente.setCognome(dipendenteDTO.getCognome());
        dipendente.setEmail(dipendenteDTO.getEmail());
        dipendente.setImgProfilo(dipendenteDTO.getImgProfilo());
        return dipendente;
    }

    //DipendenteDTO a Dipendente
    public DipendenteDTO entity_dto(Dipendente dipendente) {
        DipendenteDTO dipendenteDTO = new DipendenteDTO();
        dipendenteDTO.setUsername(dipendente.getUsername());
        dipendenteDTO.setNome(dipendente.getNome());
        dipendenteDTO.setCognome(dipendente.getCognome());
        dipendenteDTO.setEmail(dipendente.getEmail());
        dipendenteDTO.setImgProfilo(dipendente.getImgProfilo());
        return dipendenteDTO;
    }
}
