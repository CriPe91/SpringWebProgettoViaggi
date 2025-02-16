package com.example.SpringWebProgetto.dto;

import com.example.SpringWebProgetto.entity.Dipendente;
import com.example.SpringWebProgetto.entity.Viaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioniDTO {

    @NotNull(message = "La data di richiesta prenotazione è un campo obbligatorio")
    private LocalDate data;

    @NotBlank(message = "Specifica le preferenze")
    private String notePreferenzeDipendente;

    private Dipendente dipendente;
    private Viaggio viaggio;


}
