package com.example.SpringWebProgetto.service;

import com.example.SpringWebProgetto.dto.DipendenteDTO;
import com.example.SpringWebProgetto.entity.Dipendente;
import com.example.SpringWebProgetto.exception.NotFoundException;
import com.example.SpringWebProgetto.repository.DipententeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {

    @Autowired
    DipententeRepository dipendenteRepo;

    public Dipendente nuovoDipendente(DipendenteDTO dipendenteDTO){
        Dipendente dipendente = dto_entity_dipendente(dipendenteDTO);
        return dipendenteRepo.save(dipendente);
    }

    // DTO -> ENTITY
    public Dipendente dto_entity_dipendente(DipendenteDTO dipendenteDTO) {
        Dipendente dipendente= new Dipendente();
        dipendente.setNome(dipendenteDTO.getNome());
        dipendente.setCognome(dipendenteDTO.getUsername());
        dipendente.setUsername(dipendenteDTO.getUsername());
        dipendente.setEmail(dipendenteDTO.getEmail());
        return dipendente;
    }

    // ENTITY -> DTO
    public DipendenteDTO entity_dto_dipendente(Dipendente dipendente) {
        DipendenteDTO dipendenteDTO= new DipendenteDTO();

        dipendenteDTO.setNome(dipendente.getNome());
        dipendenteDTO.setCognome(dipendente.getCognome());
        dipendenteDTO.setUsername(dipendente.getUsername());
        dipendenteDTO.setEmail(dipendente.getEmail());
        return dipendenteDTO;
    }

    public Dipendente findById(long id) {
        return dipendenteRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        Dipendente dipendenteTrovato = this.findById(id);
        dipendenteRepo.delete(dipendenteTrovato);
    }

    public Dipendente findByIdAndUpdate(long id, Dipendente body) {

       Dipendente dipendenteTrovato = this.findById(id);

        dipendenteTrovato.setNome(body.getNome());
        dipendenteTrovato.setCognome(body.getCognome());
        dipendenteTrovato.setUsername(body.getUsername());
        dipendenteTrovato.setEmail(body.getEmail());

        return dipendenteRepo.save(dipendenteTrovato);
    }

    public Page<Dipendente> getDipendenti(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return dipendenteRepo.findAll(pageable);
    }



}
