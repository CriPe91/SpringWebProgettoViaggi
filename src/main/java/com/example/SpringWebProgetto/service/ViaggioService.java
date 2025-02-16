package com.example.SpringWebProgetto.service;

import com.example.SpringWebProgetto.dto.ViaggioDTO;
import com.example.SpringWebProgetto.entity.Dipendente;
import com.example.SpringWebProgetto.entity.Viaggio;
import com.example.SpringWebProgetto.exception.NotFoundException;
import com.example.SpringWebProgetto.repository.DipendenteRepository;
import com.example.SpringWebProgetto.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViaggioService {
    @Autowired
    ViaggioRepository viaggioRepo;
    @Autowired
    DipendenteRepository dipendenteRepo;


    // salva un viaggio
    public Long saveViaggio(ViaggioDTO viaggioDTO) {
        Viaggio viaggioInserito = fromViaggioDTOToViaggio(viaggioDTO);
        Viaggio viaggioSalvato = viaggioRepo.save(viaggioInserito);
        return viaggioSalvato.getId();
    }


    public Page<ViaggioDTO> getAllViaggio(Pageable page) {
        Page<Viaggio> listaViaggi = viaggioRepo.findAll(page);
        List<ViaggioDTO> listaDto = new ArrayList<>();

        for (Viaggio viaggio : listaViaggi.getContent()) {
            ViaggioDTO dto = fromViaggioToViaggioDTO(viaggio);
            listaDto.add(dto);
        }

        Page<ViaggioDTO> listaPage = new PageImpl<>(listaDto);
        return listaPage;
    }


    public ViaggioDTO findViaggioById(long id) {
        Optional<Viaggio> viaggio = viaggioRepo.findById(id);

        if (viaggio.isPresent()) {
            ViaggioDTO viaggiDto = fromViaggioToViaggioDTO(viaggio.get());
            return viaggiDto;
        } else {
            throw new NotFoundException("Nessun viaggio trovato con l'id: " + id);
        }
    }

    // sostituisci un Viaggio tramite id
    public void updateViaggio(ViaggioDTO viaggioDTO, long id) {
        Optional<Viaggio> viaggioTrovato = viaggioRepo.findById(id);

        if (viaggioTrovato.isPresent()) {
            Viaggio viaggio = viaggioTrovato.get();
            viaggio.setDestinazione(viaggioDTO.getDestinazione());
            viaggio.setDataViaggio(viaggioDTO.getDataViaggio());
            viaggio.setStatoViaggio(viaggioDTO.getStatoViaggio());
            viaggioRepo.save(viaggio);
        } else {
            throw new NotFoundException("Errore nella modifica del viaggio inserito. Nessun viaggio trovato con l'id: " + id);
        }
    }

    // cancellazione tramite id
    public String deleteViaggio(long id) {
        Optional<Viaggio> viaggioTrovato = viaggioRepo.findById(id);
        if (viaggioTrovato.isPresent()) {
            viaggioRepo.delete(viaggioTrovato.get());
            return "Viaggio con id: " + id + " eliminato con successo!";
        } else {
            throw new NotFoundException("Errore nel delete! Nessun viaggio trovato con id: " + id);
        }
    }

    //Assegna un dipendente ad un viaggio
    public void addDipendente(long viaggioId, long dipendenteId) {
        Optional<Viaggio> viaggio = viaggioRepo.findById(viaggioId);
        Optional<Dipendente> dipendente = dipendenteRepo.findById(dipendenteId);
        if (viaggio.isPresent() && dipendente.isPresent()) {
            Viaggio viaggiodaSalvare = viaggio.get();
            viaggiodaSalvare.setDipendente(dipendente.get());
            viaggioRepo.save(viaggiodaSalvare);
        } else {
            throw new NotFoundException("Viaggio o Dipendente non trovato con id: " + dipendenteId);
        }
    }


    public void modificaStatoViaggio(long viaggioId, String statoViaggio) {
        Optional<Viaggio> viaggio = viaggioRepo.findById(viaggioId);
        if (viaggio.isPresent()) {
            Viaggio viaggioDaSalvare = viaggio.get();
            viaggioDaSalvare.setStatoViaggio(statoViaggio);
            viaggioRepo.save(viaggioDaSalvare);
        } else {
            throw new NotFoundException("Viaggio non trovato con id: " + viaggioId);
        }
    }


    //Viaggio a Viaggio DTO
    public Viaggio fromViaggioDTOToViaggio(ViaggioDTO viaggioDTO) {
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(viaggioDTO.getDestinazione());
        viaggio.setDataViaggio(viaggioDTO.getDataViaggio());
        viaggio.setStatoViaggio(viaggioDTO.getStatoViaggio());
        viaggio.setDipendente(viaggioDTO.getDipendente());
        return viaggio;
    }

    //ViaggioDTo a Viaggio
    public ViaggioDTO fromViaggioToViaggioDTO(Viaggio viaggio) {
        ViaggioDTO viaggioDTO = new ViaggioDTO();
        viaggioDTO.setDestinazione(viaggio.getDestinazione());
        viaggioDTO.setDataViaggio(viaggio.getDataViaggio());
        viaggioDTO.setStatoViaggio(viaggio.getStatoViaggio());
        viaggioDTO.setDipendente(viaggio.getDipendente());
        return viaggioDTO;
    }

}
