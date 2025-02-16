package com.example.SpringWebProgetto.dto;

import com.example.SpringWebProgetto.entity.Dipendente;
import com.example.SpringWebProgetto.entity.Prenotazioni;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
public class ViaggioDTO {



    @NotNull(message = "La destinazione è un campo obbligatorio")
    @NotBlank(message = "La destinazione risulta vuoto")
    private String destinazione;


    @NotNull(message = "La data del viaggio è un campo obbligatorio")
    private LocalDate dataViaggio;

    @NotBlank(message = "Lo stato del viaggio risulta vuoto")
    private String statoViaggio;

    private Dipendente dipendente;


}
