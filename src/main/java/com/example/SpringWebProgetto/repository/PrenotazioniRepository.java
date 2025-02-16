package com.example.SpringWebProgetto.repository;

import com.example.SpringWebProgetto.entity.Dipendente;
import com.example.SpringWebProgetto.entity.Prenotazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazioni,Long> {
    List<Prenotazioni> findByDataAndDipendente(LocalDate data, Dipendente dipendente);

    List<Prenotazioni> findByDipendente(Dipendente dipendente);
}
