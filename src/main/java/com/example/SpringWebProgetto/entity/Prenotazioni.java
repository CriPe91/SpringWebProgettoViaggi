package com.example.SpringWebProgetto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Prenotazioni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPrenotazione;

    @Column(nullable = false)
    private LocalDate dataRichiestaPrenotazione;
    private String notePreferenzeDipendente;


}
