package com.example.SpringWebProgetto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class PrenotazioniDTO {

    @NotNull(message = "La data di richiesta prenotazione è un campo obbligatorio")
    @NotBlank(message = "La data di richiesta prenotazione risulta vuoto")
    private LocalDate dataRichiestaPrenotazione;

    @NotBlank(message = "Specifica le preferenze")
    private String notePreferenzeDipendente;


}
