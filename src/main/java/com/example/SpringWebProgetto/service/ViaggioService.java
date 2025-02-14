package com.example.SpringWebProgetto.service;


import com.example.SpringWebProgetto.dto.ViaggioDTO;
import com.example.SpringWebProgetto.entity.Viaggio;
import com.example.SpringWebProgetto.exception.NotFoundException;
import com.example.SpringWebProgetto.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {

    @Autowired
    ViaggioRepository viaggioRepo;

    public Viaggio nuovoViaggio(ViaggioDTO viaggioDTO){

        Viaggio viaggio = dto_entity_viaggio(viaggioDTO);
        return viaggioRepo.save(viaggio);
    }

    // DTO -> ENTITY
    public Viaggio dto_entity_viaggio(ViaggioDTO viaggioDTO) {
        Viaggio viaggio= new Viaggio();
        viaggio.setDataViaggio(viaggioDTO.getDataViaggio());
        viaggio.setDestinazione(viaggioDTO.getDestinazione());
        viaggio.setStatoViaggio(viaggioDTO.getStatoViaggio());
        return viaggio;
    }

    // ENTITY -> DTO
    public ViaggioDTO entity_dto_viaggio(Viaggio viaggio) {
        ViaggioDTO viaggioDTO= new ViaggioDTO();

        viaggioDTO.setDataViaggio(viaggio.getDataViaggio());
        viaggioDTO.setDestinazione(viaggio.getDestinazione());
        viaggioDTO.setStatoViaggio(viaggio.getStatoViaggio());
        return viaggioDTO;
    }

    public Viaggio findById(long id) {
        return viaggioRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        Viaggio viaggioTrovato = this.findById(id);
        viaggioRepo.delete(viaggioTrovato);
    }

    public Viaggio findByIdAndUpdate(long id, Viaggio body) {

        Viaggio viaggioTrovato = this.findById(id);

        viaggioTrovato.setDataViaggio(body.getDataViaggio());
        viaggioTrovato.setDestinazione(body.getDestinazione());
        viaggioTrovato.setStatoViaggio(body.getStatoViaggio());

        return viaggioRepo.save(viaggioTrovato);
    }

    public Page<Viaggio> getViaggi(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return viaggioRepo.findAll(pageable);
    }


}
