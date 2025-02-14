package com.example.SpringWebProgetto.service;

import com.example.SpringWebProgetto.dto.DipendenteDTO;
import com.example.SpringWebProgetto.entity.Dipendente;
import com.example.SpringWebProgetto.repository.DipententeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {

    @Autowired
    DipententeRepository dipendenteRepo;

    public Long nuovoDipendente(DipendenteDTO dipendenteDTO){
        Dipendente dipendente = dto_entity(dipendenteDTO);
        return dipendenteRepo.save(dipendente).getIdDipendente();
    }

    // DTO -> ENTITY
    public Dipendente dto_entity(DipendenteDTO dipendenteDTO) {
        Dipendente dipendente= new Dipendente();
        dipendente.setNome(dipendenteDTO.getNome());
        dipendente.setCognome(dipendenteDTO.getUsername());
        dipendente.setUsername(dipendenteDTO.getUsername());
        dipendente.setEmail(dipendenteDTO.getEmail());
        return dipendente;
    }

    // ENTITY -> DTO
    public DipendenteDTO entity_dto(Dipendente dipendente) {
        DipendenteDTO dipendenteDTO= new DipendenteDTO();

        dipendenteDTO.setNome(dipendente.getNome());
        dipendenteDTO.setCognome(dipendente.getCognome());
        dipendenteDTO.setUsername(dipendente.getUsername());
        dipendenteDTO.setEmail(dipendente.getEmail());
        return dipendenteDTO;
    }



}
