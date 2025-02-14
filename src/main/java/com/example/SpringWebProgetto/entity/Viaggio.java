package com.example.SpringWebProgetto.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String destinazione;
    private LocalDate dataViaggio;
    private String statoViaggio;

    @OneToMany
    @JoinColumn(name = "id_viaggio")
    private List<Prenotazioni> listaPrenotazione;

}
