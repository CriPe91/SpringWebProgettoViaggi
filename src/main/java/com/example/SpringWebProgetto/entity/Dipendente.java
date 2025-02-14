package com.example.SpringWebProgetto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDipendente;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false,unique = true)
    private String email;

    @OneToMany
    @JoinColumn(name = "id_dipendente")
    private List<Prenotazioni> listaPrenotazione;


}
